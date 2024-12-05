package com.ruoyi.chat.service.impl;

import java.util.List;

import com.ruoyi.chat.constant.ChatGroupUserConstant;
import com.ruoyi.chat.domain.ChatGroup;
import com.ruoyi.chat.dto.ChatGroupUserDto;
import com.ruoyi.chat.mapper.ChatGroupMapper;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.uuid.SnowflakeUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.common.security.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.chat.mapper.ChatGroupUserMapper;
import com.ruoyi.chat.domain.ChatGroupUser;
import com.ruoyi.chat.service.IChatGroupUserService;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private ChatGroupMapper chatGroupMapper;

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

    @Override
    public int addGroupUser(ChatGroupUserDto chatGroupUserDto) {
        // 校验群组是否存在
        ChatGroup queryChatGroup = new ChatGroup();
        queryChatGroup.setGroupId(chatGroupUserDto.getGroupId());
        queryChatGroup.setDelFlag("0");
        if (CollectionUtils.isEmpty(chatGroupMapper.selectChatGroupList(queryChatGroup))) {
            throw new RuntimeException("群：" + chatGroupUserDto.getGroupId() + "不存在");
        }
        // 获取用户信息
        Long userId = UserUtils.getUserByUsername(chatGroupUserDto.getUserName()).getUserId();
        // 校验用户是否已在群和是否曾经进群
        ChatGroupUser queryChatGroupUser = new ChatGroupUser();
        queryChatGroupUser.setGroupId(chatGroupUserDto.getGroupId());
        queryChatGroupUser.setUserId(userId);
        List<ChatGroupUser> chatGroupUserList = chatGroupUserMapper.selectChatGroupUserList(queryChatGroupUser);
        // 不在群，且未曾进群
        if (CollectionUtils.isEmpty(chatGroupUserList)) {
            // 封装群成员数据并添加
            ChatGroupUser chatGroupUser = new ChatGroupUser();
            chatGroupUser.setId(String.valueOf(SnowflakeUtils.nextId()));
            chatGroupUser.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
            chatGroupUser.setCreateTime(DateUtils.getNowDate());
            chatGroupUser.setGroupId(chatGroupUserDto.getGroupId());
            chatGroupUser.setUserId(userId);
            chatGroupUser.setGroupUserRole(ChatGroupUserConstant.GroupUserRole.COMMON.getId());
            return chatGroupUserMapper.insertChatGroupUser(chatGroupUser);
        }
        // 在群和是否曾经进群
        if ("0".equals(chatGroupUserList.get(0).getDelFlag()) && "0".equals(chatGroupUserList.get(0).getLeaveFlag())) {
            // 在群的用户
            throw new RuntimeException("用户已在群，无需再次添加");
        } else {
            // 曾经在群的用户恢复在群状态
            return chatGroupUserMapper.resetChatGroupUserNormal(chatGroupUserList.get(0).getId(), String.valueOf(SecurityUtils.getUserId()), DateUtils.getNowDate());
        }
    }

}
