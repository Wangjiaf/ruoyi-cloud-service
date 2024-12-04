package com.ruoyi.chat.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.chat.mapper.ChatGroupUserMapper;
import com.ruoyi.chat.domain.ChatGroupUser;
import com.ruoyi.chat.service.IChatGroupUserService;

/**
 * 群成员数据Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-02
 */
@Service
public class ChatGroupUserServiceImpl implements IChatGroupUserService
{

    @Autowired
    private ChatGroupUserMapper chatGroupUserMapper;

    /**
     * 查询群成员数据
     *
     * @param id 群成员数据主键
     * @return 群成员数据
     */
    @Override
    public ChatGroupUser selectChatGroupUserById(String id)
    {
        return chatGroupUserMapper.selectChatGroupUserById(id);
    }

    /**
     * 查询群成员数据列表
     *
     * @param chatGroupUser 群成员数据
     * @return 群成员数据
     */
    @Override
    public List<ChatGroupUser> selectChatGroupUserList(ChatGroupUser chatGroupUser)
    {
        return chatGroupUserMapper.selectChatGroupUserList(chatGroupUser);
    }

    /**
     * 新增群成员数据
     *
     * @param chatGroupUser 群成员数据
     * @return 结果
     */
    @Override
    public int insertChatGroupUser(ChatGroupUser chatGroupUser)
    {
        chatGroupUser.setCreateTime(DateUtils.getNowDate());
        return chatGroupUserMapper.insertChatGroupUser(chatGroupUser);
    }

    /**
     * 修改群成员数据
     *
     * @param chatGroupUser 群成员数据
     * @return 结果
     */
    @Override
    public int updateChatGroupUser(ChatGroupUser chatGroupUser)
    {
        chatGroupUser.setUpdateTime(DateUtils.getNowDate());
        return chatGroupUserMapper.updateChatGroupUser(chatGroupUser);
    }

    /**
     * 批量删除群成员数据
     *
     * @param ids 需要删除的群成员数据主键
     * @return 结果
     */
    @Override
    public int deleteChatGroupUserByIds(String[] ids)
    {
        return chatGroupUserMapper.deleteChatGroupUserByIds(ids);
    }

    /**
     * 删除群成员数据信息
     *
     * @param id 群成员数据主键
     * @return 结果
     */
    @Override
    public int deleteChatGroupUserById(String id)
    {
        return chatGroupUserMapper.deleteChatGroupUserById(id);
    }

}
