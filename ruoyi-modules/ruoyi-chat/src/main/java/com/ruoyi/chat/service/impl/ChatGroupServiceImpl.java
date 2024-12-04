package com.ruoyi.chat.service.impl;

import java.util.List;

import com.ruoyi.chat.constant.ChatGroupUserConstant;
import com.ruoyi.chat.domain.ChatGroupUser;
import com.ruoyi.chat.mapper.ChatGroupUserMapper;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.uuid.SnowflakeUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.chat.mapper.ChatGroupMapper;
import com.ruoyi.chat.domain.ChatGroup;
import com.ruoyi.chat.service.IChatGroupService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 群组主数据Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-02
 */
@Service
public class ChatGroupServiceImpl implements IChatGroupService
{

    @Autowired
    private ChatGroupMapper chatGroupMapper;

    @Autowired
    private ChatGroupUserMapper chatGroupUserMapper;

    /**
     * 查询群组主数据
     *
     * @param id 群组主数据主键
     * @return 群组主数据
     */
    @Override
    public ChatGroup selectChatGroupById(String id)
    {
        return chatGroupMapper.selectChatGroupById(id);
    }

    /**
     * 查询群组主数据列表
     *
     * @param chatGroup 群组主数据
     * @return 群组主数据
     */
    @Override
    public List<ChatGroup> selectChatGroupList(ChatGroup chatGroup)
    {
        return chatGroupMapper.selectChatGroupList(chatGroup);
    }

    /**
     * 新增群组主数据
     *
     * @param chatGroup 群组主数据
     * @return 结果
     */
    @Override
    public int insertChatGroup(ChatGroup chatGroup)
    {
        chatGroup.setCreateTime(DateUtils.getNowDate());
        return chatGroupMapper.insertChatGroup(chatGroup);
    }

    /**
     * 修改群组主数据
     *
     * @param chatGroup 群组主数据
     * @return 结果
     */
    @Override
    public int updateChatGroup(ChatGroup chatGroup)
    {
        chatGroup.setUpdateTime(DateUtils.getNowDate());
        return chatGroupMapper.updateChatGroup(chatGroup);
    }

    /**
     * 批量删除群组主数据
     *
     * @param ids 需要删除的群组主数据主键
     * @return 结果
     */
    @Override
    public int deleteChatGroupByIds(String[] ids)
    {
        return chatGroupMapper.deleteChatGroupByIds(ids);
    }

    /**
     * 删除群组主数据信息
     *
     * @param id 群组主数据主键
     * @return 结果
     */
    @Override
    public int deleteChatGroupById(String id)
    {
        return chatGroupMapper.deleteChatGroupById(id);
    }

    @Override
    @Transactional
    public AjaxResult addGroup(ChatGroup chatGroup)
    {
        // 新建群
        chatGroup.setId(String.valueOf(SnowflakeUtils.nextId()));
        chatGroup.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
        chatGroup.setCreateTime(DateUtils.getNowDate());
        chatGroup.setGroupId(Long.parseLong(chatGroup.getId()));
        chatGroupMapper.insertChatGroup(chatGroup);
        // 自动设定群主
        ChatGroupUser chatGroupUser = new ChatGroupUser();
        chatGroupUser.setId(String.valueOf(SnowflakeUtils.nextId()));
        chatGroupUser.setCreateBy(chatGroup.getCreateBy());
        chatGroupUser.setCreateTime(chatGroup.getCreateTime());
        chatGroupUser.setGroupId(chatGroup.getGroupId());
        chatGroupUser.setUserId(SecurityUtils.getUserId());
        chatGroupUser.setGroupUserRole(ChatGroupUserConstant.GroupUserRole.OWNER.getId());
        chatGroupUserMapper.insertChatGroupUser(chatGroupUser);
        return AjaxResult.success(chatGroup.getId());
    }

}
