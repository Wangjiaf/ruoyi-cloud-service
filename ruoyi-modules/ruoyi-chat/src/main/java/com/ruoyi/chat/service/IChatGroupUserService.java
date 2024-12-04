package com.ruoyi.chat.service;

import java.util.List;
import com.ruoyi.chat.domain.ChatGroupUser;

/**
 * 群成员数据Service接口
 *
 * @author ruoyi
 * @date 2024-12-02
 */
public interface IChatGroupUserService
{
    /**
     * 查询群成员数据
     *
     * @param id 群成员数据主键
     * @return 群成员数据
     */
    public ChatGroupUser selectChatGroupUserById(String id);

    /**
     * 查询群成员数据列表
     *
     * @param chatGroupUser 群成员数据
     * @return 群成员数据集合
     */
    public List<ChatGroupUser> selectChatGroupUserList(ChatGroupUser chatGroupUser);

    /**
     * 新增群成员数据
     *
     * @param chatGroupUser 群成员数据
     * @return 结果
     */
    public int insertChatGroupUser(ChatGroupUser chatGroupUser);

    /**
     * 修改群成员数据
     *
     * @param chatGroupUser 群成员数据
     * @return 结果
     */
    public int updateChatGroupUser(ChatGroupUser chatGroupUser);

    /**
     * 批量删除群成员数据
     *
     * @param ids 需要删除的群成员数据主键集合
     * @return 结果
     */
    public int deleteChatGroupUserByIds(String[] ids);

    /**
     * 删除群成员数据信息
     *
     * @param id 群成员数据主键
     * @return 结果
     */
    public int deleteChatGroupUserById(String id);

}
