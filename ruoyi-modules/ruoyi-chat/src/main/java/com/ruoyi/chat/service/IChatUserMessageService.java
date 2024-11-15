package com.ruoyi.chat.service;

import java.util.List;
import com.ruoyi.chat.domain.ChatUserMessage;

/**
 * 好友聊天消息Service接口
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
public interface IChatUserMessageService 
{
    /**
     * 查询好友聊天消息
     * 
     * @param id 好友聊天消息主键
     * @return 好友聊天消息
     */
    public ChatUserMessage selectChatUserMessageById(String id);

    /**
     * 查询好友聊天消息列表
     * 
     * @param chatUserMessage 好友聊天消息
     * @return 好友聊天消息集合
     */
    public List<ChatUserMessage> selectChatUserMessageList(ChatUserMessage chatUserMessage);

    /**
     * 新增好友聊天消息
     * 
     * @param chatUserMessage 好友聊天消息
     * @return 结果
     */
    public int insertChatUserMessage(ChatUserMessage chatUserMessage);

    /**
     * 修改好友聊天消息
     * 
     * @param chatUserMessage 好友聊天消息
     * @return 结果
     */
    public int updateChatUserMessage(ChatUserMessage chatUserMessage);

    /**
     * 批量删除好友聊天消息
     * 
     * @param ids 需要删除的好友聊天消息主键集合
     * @return 结果
     */
    public int deleteChatUserMessageByIds(String[] ids);

    /**
     * 删除好友聊天消息信息
     * 
     * @param id 好友聊天消息主键
     * @return 结果
     */
    public int deleteChatUserMessageById(String id);
}
