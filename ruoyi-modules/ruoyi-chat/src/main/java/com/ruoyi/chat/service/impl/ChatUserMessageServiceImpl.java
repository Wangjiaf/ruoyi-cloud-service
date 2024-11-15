package com.ruoyi.chat.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.chat.mapper.ChatUserMessageMapper;
import com.ruoyi.chat.domain.ChatUserMessage;
import com.ruoyi.chat.service.IChatUserMessageService;

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
        chatUserMessage.setCreateTime(DateUtils.getNowDate());
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
}
