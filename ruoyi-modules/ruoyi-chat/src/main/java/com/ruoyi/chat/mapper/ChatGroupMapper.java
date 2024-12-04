package com.ruoyi.chat.mapper;

import java.util.List;
import com.ruoyi.chat.domain.ChatGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群组主数据Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-02
 */
@Mapper
public interface ChatGroupMapper
{
    /**
     * 查询群组主数据
     *
     * @param id 群组主数据主键
     * @return 群组主数据
     */
    public ChatGroup selectChatGroupById(String id);

    /**
     * 查询群组主数据列表
     *
     * @param chatGroup 群组主数据
     * @return 群组主数据集合
     */
    public List<ChatGroup> selectChatGroupList(ChatGroup chatGroup);

    /**
     * 新增群组主数据
     *
     * @param chatGroup 群组主数据
     * @return 结果
     */
    public int insertChatGroup(ChatGroup chatGroup);

    /**
     * 修改群组主数据
     *
     * @param chatGroup 群组主数据
     * @return 结果
     */
    public int updateChatGroup(ChatGroup chatGroup);

    /**
     * 删除群组主数据
     *
     * @param id 群组主数据主键
     * @return 结果
     */
    public int deleteChatGroupById(String id);

    /**
     * 批量删除群组主数据
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChatGroupByIds(String[] ids);

}
