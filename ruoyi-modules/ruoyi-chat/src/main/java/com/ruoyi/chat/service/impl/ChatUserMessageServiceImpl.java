package com.ruoyi.chat.service.impl;

import java.util.List;

import com.ruoyi.chat.domain.ChatTip;
import com.ruoyi.chat.mapper.ChatTipMapper;
import com.ruoyi.chat.utils.ChatTipUtils;
import com.ruoyi.chat.utils.ChatUserMessageUtils;
import com.ruoyi.chat.vo.ChatUserMessageVo;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.SnowflakeUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
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
        chatUserMessage.setId(String.valueOf(SnowflakeUtils.nextId()));
        chatUserMessage.setCreateTime(DateUtils.getNowDate());
        chatUserMessage.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        chatUserMessageMapper.insertChatUserMessage(chatUserMessage);
        ChatTip chatTip = chatTipMapper.selectChatTipBy_userId_tipFromId("P2P", chatUserMessage.getToUserId(), chatUserMessage.getFromUserId());
        if (null == chatTip) {
            chatTip = new ChatTip();
            chatTip.setCreateTime(chatUserMessage.getCreateTime());
            chatTip.setCreateBy(chatUserMessage.getCreateBy());
            chatTip.setChatType("P2P");
            chatTip.setUserId(chatUserMessage.getToUserId());
            chatTip.setTipFromId(chatUserMessage.getFromUserId());
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
        ChatTipUtils.socketSendMessage(chatUserMessage.getToUserId() + "", "0");
        boolean onChatWindowFlag = ChatUserMessageUtils.socketSendMessage(chatUserMessage.getToUserId() + "", "0");
        // 消息接收方若正在对话框界面，则标记消息为已读
        if (onChatWindowFlag) {
            chatTipMapper.resetChatTipCount(chatTip.getId(), Long.parseLong(chatUserMessage.getCreateBy()), chatUserMessage.getCreateTime());
        }
        return 1;
    }

}
