package com.ruoyi.chat.service.impl;

import java.util.List;

import com.ruoyi.chat.domain.ChatTip;
import com.ruoyi.chat.domain.ChatUserRelation;
import com.ruoyi.chat.mapper.ChatTipMapper;
import com.ruoyi.chat.mapper.ChatUserRelationMapper;
import com.ruoyi.chat.utils.ChatTipUtils;
import com.ruoyi.chat.utils.ChatUserMessageUtils;
import com.ruoyi.chat.vo.ChatUserMessageVo;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.SnowflakeUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.chat.mapper.ChatUserMessageMapper;
import com.ruoyi.chat.domain.ChatUserMessage;
import com.ruoyi.chat.service.IChatUserMessageService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 好友聊天消息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@Service
public class ChatUserMessageServiceImpl implements IChatUserMessageService 
{
    @Autowired
    private ChatUserMessageMapper chatUserMessageMapper;

    @Autowired
    private ChatTipMapper chatTipMapper;

    @Autowired
    private ChatUserRelationMapper chatUserRelationMapper;

    /**
     * 查询好友聊天消息
     * 
     * @param id 好友聊天消息主键
     * @return 好友聊天消息
     */
    @Override
    public ChatUserMessage selectChatUserMessageById(String id)
    {
        return chatUserMessageMapper.selectChatUserMessageById(id);
    }

    /**
     * 查询好友聊天消息列表
     * 
     * @param chatUserMessage 好友聊天消息
     * @return 好友聊天消息
     */
    @Override
    public List<ChatUserMessage> selectChatUserMessageList(ChatUserMessage chatUserMessage)
    {
        return chatUserMessageMapper.selectChatUserMessageList(chatUserMessage);
    }

    /**
     * 新增好友聊天消息
     * 
     * @param chatUserMessage 好友聊天消息
     * @return 结果
     */
    @Override
    public int insertChatUserMessage(ChatUserMessage chatUserMessage)
    {
        chatUserMessage.setId(String.valueOf(SnowflakeUtils.nextId()));
        chatUserMessage.setCreateTime(DateUtils.getNowDate());
        chatUserMessage.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        return chatUserMessageMapper.insertChatUserMessage(chatUserMessage);
    }

    /**
     * 修改好友聊天消息
     * 
     * @param chatUserMessage 好友聊天消息
     * @return 结果
     */
    @Override
    public int updateChatUserMessage(ChatUserMessage chatUserMessage)
    {
        chatUserMessage.setUpdateTime(DateUtils.getNowDate());
        return chatUserMessageMapper.updateChatUserMessage(chatUserMessage);
    }

    /**
     * 批量删除好友聊天消息
     * 
     * @param ids 需要删除的好友聊天消息主键
     * @return 结果
     */
    @Override
    public int deleteChatUserMessageByIds(String[] ids)
    {
        return chatUserMessageMapper.deleteChatUserMessageByIds(ids);
    }

    /**
     * 删除好友聊天消息信息
     * 
     * @param id 好友聊天消息主键
     * @return 结果
     */
    @Override
    public int deleteChatUserMessageById(String id)
    {
        return chatUserMessageMapper.deleteChatUserMessageById(id);
    }

    @Override
    public List<ChatUserMessageVo> selectChatUserMessageVoList(ChatUserMessageVo chatUserMessageVo) {
        return chatUserMessageMapper.selectChatUserMessageVoList(chatUserMessageVo);
    }

    @Override
    @Transactional
    public int sendChatUserMessage(ChatUserMessage chatUserMessage) {
        // 校验对方是否为好友
        valid(chatUserMessage);
        // 聊天记录内容存储
        chatUserMessage.setId(String.valueOf(SnowflakeUtils.nextId()));
        chatUserMessage.setCreateTime(DateUtils.getNowDate());
        chatUserMessage.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        chatUserMessageMapper.insertChatUserMessage(chatUserMessage);
        // 双方新增或更新消息提示数据
        ChatTip toChatTip = saveChatTip(chatUserMessage, chatUserMessage.getToUserId(), chatUserMessage.getFromUserId());
        ChatTip fromChatTip = saveChatTip(chatUserMessage, chatUserMessage.getFromUserId(), chatUserMessage.getToUserId());
        // 消息提示列表推送给对方
        ChatTipUtils.socketSendMessage(chatUserMessage.getToUserId() + "", "0");
        // 对话框列表推送给对方
        boolean onChatWindowFlag = ChatUserMessageUtils.socketSendMessage(chatUserMessage.getToUserId() + "", "0");
        // 对方若正在对话框界面，则标记消息为已读
        if (onChatWindowFlag) {
            chatTipMapper.resetChatTipCount(toChatTip.getId(), Long.parseLong(chatUserMessage.getCreateBy()), chatUserMessage.getCreateTime());
        }
        // 己方消息直接标记为已读
        chatTipMapper.resetChatTipCount(fromChatTip.getId(), Long.parseLong(chatUserMessage.getCreateBy()), chatUserMessage.getCreateTime());
        return 1;
    }

    private void valid(ChatUserMessage chatUserMessage) {
        // 校验对方是否为好友
        ChatUserRelation queryChatUserRelation = new ChatUserRelation();
        queryChatUserRelation.setUserId(chatUserMessage.getFromUserId());
        queryChatUserRelation.setRelationUserId(chatUserMessage.getToUserId());
        queryChatUserRelation.setDelFlag("0");
        List<ChatUserRelation> validChatUserRelationList = chatUserRelationMapper.selectChatUserRelationList(queryChatUserRelation);
        if (CollectionUtils.isEmpty(validChatUserRelationList)) {
            throw new RuntimeException("对方已不是你的好友，无法发送消息");
        }
    }

    private ChatTip saveChatTip(ChatUserMessage chatUserMessage, Long userIdLeft, Long userIdRight) {
        // 封装消息提示数据，表里有则更新，没有则新增，保证每人与对方最多1条数据
        ChatTip chatTip = chatTipMapper.selectChatTipBy_userId_tipFromId("P2P", userIdLeft, userIdRight);
        if (null == chatTip) {
            chatTip = new ChatTip();
            chatTip.setCreateTime(chatUserMessage.getCreateTime());
            chatTip.setCreateBy(chatUserMessage.getCreateBy());
            chatTip.setChatType("P2P");
            chatTip.setUserId(userIdLeft);
            chatTip.setTipFromId(userIdRight);
            chatTip.setUnReadCount(0);
        }
        chatTip.setTipContent(ChatTipUtils.cutTipContent(chatUserMessage.getMessageContent()));
        chatTip.setUnReadCount(chatTip.getUnReadCount() + 1);
        if (StringUtils.isEmpty(chatTip.getId())) {
            chatTip.setId(String.valueOf(SnowflakeUtils.nextId()));
            chatTipMapper.insertChatTip(chatTip);
        } else {
            chatTip.setDelFlag("0");
            chatTip.setUpdateTime(chatUserMessage.getCreateTime());
            chatTip.setUpdateBy(chatUserMessage.getCreateBy());
            chatTipMapper.updateChatTip(chatTip);
        }
        return chatTip;
    }

}
