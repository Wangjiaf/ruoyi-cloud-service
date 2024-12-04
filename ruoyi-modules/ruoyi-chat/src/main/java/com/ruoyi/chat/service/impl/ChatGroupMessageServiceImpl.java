package com.ruoyi.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.chat.domain.*;
import com.ruoyi.chat.mapper.ChatGroupUserMapper;
import com.ruoyi.chat.mapper.ChatTipMapper;
import com.ruoyi.chat.utils.ChatGroupMessageUtils;
import com.ruoyi.chat.utils.ChatTipUtils;
import com.ruoyi.chat.vo.ChatGroupMessageVo;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.SnowflakeUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.chat.mapper.ChatGroupMessageMapper;
import com.ruoyi.chat.service.IChatGroupMessageService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 群消息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-02
 */
@Service
public class ChatGroupMessageServiceImpl implements IChatGroupMessageService
{

    @Autowired
    private ChatGroupMessageMapper chatGroupMessageMapper;

    @Autowired
    private ChatGroupUserMapper chatGroupUserMapper;

    @Autowired
    private ChatTipMapper chatTipMapper;

    /**
     * 查询群消息
     *
     * @param id 群消息主键
     * @return 群消息
     */
    @Override
    public ChatGroupMessage selectChatGroupMessageById(String id)
    {
        return chatGroupMessageMapper.selectChatGroupMessageById(id);
    }

    /**
     * 查询群消息列表
     *
     * @param chatGroupMessage 群消息
     * @return 群消息
     */
    @Override
    public List<ChatGroupMessage> selectChatGroupMessageList(ChatGroupMessage chatGroupMessage)
    {
        return chatGroupMessageMapper.selectChatGroupMessageList(chatGroupMessage);
    }

    /**
     * 新增群消息
     *
     * @param chatGroupMessage 群消息
     * @return 结果
     */
    @Override
    public int insertChatGroupMessage(ChatGroupMessage chatGroupMessage)
    {
        chatGroupMessage.setCreateTime(DateUtils.getNowDate());
        return chatGroupMessageMapper.insertChatGroupMessage(chatGroupMessage);
    }

    /**
     * 修改群消息
     *
     * @param chatGroupMessage 群消息
     * @return 结果
     */
    @Override
    public int updateChatGroupMessage(ChatGroupMessage chatGroupMessage)
    {
        chatGroupMessage.setUpdateTime(DateUtils.getNowDate());
        return chatGroupMessageMapper.updateChatGroupMessage(chatGroupMessage);
    }

    /**
     * 批量删除群消息
     *
     * @param ids 需要删除的群消息主键
     * @return 结果
     */
    @Override
    public int deleteChatGroupMessageByIds(String[] ids)
    {
        return chatGroupMessageMapper.deleteChatGroupMessageByIds(ids);
    }

    /**
     * 删除群消息信息
     *
     * @param id 群消息主键
     * @return 结果
     */
    @Override
    public int deleteChatGroupMessageById(String id)
    {
        return chatGroupMessageMapper.deleteChatGroupMessageById(id);
    }

    @Override
    public List<ChatGroupMessageVo> selectChatGroupMessageVoList(ChatGroupMessageVo chatGroupMessageVo) {
        return chatGroupMessageMapper.selectChatGroupMessageVoList(chatGroupMessageVo);
    }

    @Override
    @Transactional
    public int sendChatGroupMessage(ChatGroupMessage chatGroupMessage) {
        // 校验是否在群
        valid(chatGroupMessage);
        // 聊天记录内容存储
        chatGroupMessage.setId(String.valueOf(SnowflakeUtils.nextId()));
        chatGroupMessage.setCreateTime(DateUtils.getNowDate());
        chatGroupMessage.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        chatGroupMessageMapper.insertChatGroupMessage(chatGroupMessage);
        // 群成员新增或更新消息提示数据
        List<ChatTip> chatTipList = saveChatTip(chatGroupMessage, chatGroupMessage.getGroupId());
        // 消息提示列表推送给群成员
        for (ChatTip item : chatTipList) {
            ChatTipUtils.socketSendMessage(item.getUserId() + "", "0");
            boolean onChatWindowFlag = ChatGroupMessageUtils.socketSendMessage(item.getUserId() + "", "0");
            // 群成员若正在对话框界面，则标记消息为已读
            if (onChatWindowFlag) {
                chatTipMapper.resetChatTipCount(item.getId(), Long.parseLong(chatGroupMessage.getCreateBy()), chatGroupMessage.getCreateTime());
            }
        }
        return 1;
    }

    private void valid(ChatGroupMessage chatGroupMessage) {
        // 校验是否在群
        ChatGroupUser queryChatGroupUser = new ChatGroupUser();
        queryChatGroupUser.setGroupId(chatGroupMessage.getGroupId());
        queryChatGroupUser.setUserId(chatGroupMessage.getFromUserId());
        queryChatGroupUser.setLeaveFlag("0");
        queryChatGroupUser.setDelFlag("0");
        List<ChatGroupUser> validChatGroupUserList = chatGroupUserMapper.selectChatGroupUserList(queryChatGroupUser);
        if (CollectionUtils.isEmpty(validChatGroupUserList)) {
            throw new RuntimeException("你已不在群，无法发送消息");
        }
    }

    private List<ChatTip> saveChatTip(ChatGroupMessage chatGroupMessage, Long groupId) {
        List<ChatTip> chatTipList = new ArrayList<ChatTip>();
        // 获取所有群成员
        ChatGroupUser chatGroupUser = new ChatGroupUser();
        chatGroupUser.setGroupId(groupId);
        chatGroupUser.setLeaveFlag("0");
        chatGroupUser.setDelFlag("0");
        List<ChatGroupUser> chatGroupUserList = chatGroupUserMapper.selectChatGroupUserList(chatGroupUser);
        for (ChatGroupUser item : chatGroupUserList) {
            // 封装消息提示数据，表里有则更新，没有则新增，保证每人与每群最多1条数据
            ChatTip chatTip = chatTipMapper.selectChatTipBy_userId_tipFromId("G2P", item.getUserId(), groupId);
            if (null == chatTip) {
                chatTip = new ChatTip();
                chatTip.setCreateTime(chatGroupMessage.getCreateTime());
                chatTip.setCreateBy(chatGroupMessage.getCreateBy());
                chatTip.setChatType("G2P");
                chatTip.setUserId(item.getUserId());
                chatTip.setTipFromId(groupId);
                chatTip.setUnReadCount(0);
            }
            chatTip.setTipContent(ChatTipUtils.cutTipContent(chatGroupMessage.getMessageContent()));
            chatTip.setUnReadCount(chatTip.getUnReadCount() + 1);
            if (StringUtils.isEmpty(chatTip.getId())) {
                chatTip.setId(String.valueOf(SnowflakeUtils.nextId()));
                chatTipMapper.insertChatTip(chatTip);
            } else {
                chatTip.setDelFlag("0");
                chatTip.setUpdateTime(chatGroupMessage.getCreateTime());
                chatTip.setUpdateBy(chatGroupMessage.getCreateBy());
                chatTipMapper.updateChatTip(chatTip);
            }
            chatTipList.add(chatTip);
        }
        return chatTipList;
    }

}
