package com.ruoyi.chat.service.impl;

import java.util.List;

import com.ruoyi.chat.vo.ChatUserRelationVo;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.chat.mapper.ChatUserRelationMapper;
import com.ruoyi.chat.domain.ChatUserRelation;
import com.ruoyi.chat.service.IChatUserRelationService;

/**
 * 好友关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@Service
public class ChatUserRelationServiceImpl implements IChatUserRelationService 
{
    @Autowired
    private ChatUserRelationMapper chatUserRelationMapper;

    /**
     * 查询好友关系
     * 
     * @param id 好友关系主键
     * @return 好友关系
     */
    @Override
    public ChatUserRelation selectChatUserRelationById(String id)
    {
        return chatUserRelationMapper.selectChatUserRelationById(id);
    }

    /**
     * 查询好友关系列表
     * 
     * @param chatUserRelation 好友关系
     * @return 好友关系
     */
    @Override
    public List<ChatUserRelation> selectChatUserRelationList(ChatUserRelation chatUserRelation)
    {
        return chatUserRelationMapper.selectChatUserRelationList(chatUserRelation);
    }

    @Override
    public List<ChatUserRelationVo> selectChatUserRelationVoList(ChatUserRelationVo chatUserRelationvo)
    {
        return chatUserRelationMapper.selectChatUserRelationVoList(chatUserRelationvo);
    }

    /**
     * 新增好友关系
     * 
     * @param chatUserRelation 好友关系
     * @return 结果
     */
    @Override
    public int insertChatUserRelation(ChatUserRelation chatUserRelation)
    {
        chatUserRelation.setCreateTime(DateUtils.getNowDate());
        return chatUserRelationMapper.insertChatUserRelation(chatUserRelation);
    }

    /**
     * 修改好友关系
     * 
     * @param chatUserRelation 好友关系
     * @return 结果
     */
    @Override
    public int updateChatUserRelation(ChatUserRelation chatUserRelation)
    {
        chatUserRelation.setUpdateTime(DateUtils.getNowDate());
        return chatUserRelationMapper.updateChatUserRelation(chatUserRelation);
    }

    /**
     * 批量删除好友关系
     * 
     * @param ids 需要删除的好友关系主键
     * @return 结果
     */
    @Override
    public int deleteChatUserRelationByIds(String[] ids)
    {
        return chatUserRelationMapper.deleteChatUserRelationByIds(ids);
    }

    /**
     * 删除好友关系信息
     * 
     * @param id 好友关系主键
     * @return 结果
     */
    @Override
    public int deleteChatUserRelationById(String id)
    {
        return chatUserRelationMapper.deleteChatUserRelationById(id);
    }
}
