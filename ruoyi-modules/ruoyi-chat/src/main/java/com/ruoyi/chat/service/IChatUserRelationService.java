package com.ruoyi.chat.service;

import java.util.List;
import com.ruoyi.chat.domain.ChatUserRelation;
import com.ruoyi.chat.dto.ChatUserRelationDto;
import com.ruoyi.chat.vo.ChatUserRelationVo;

/**
 * 好友关系Service接口
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
public interface IChatUserRelationService 
{
    /**
     * 查询好友关系
     * 
     * @param id 好友关系主键
     * @return 好友关系
     */
    public ChatUserRelation selectChatUserRelationById(String id);

    /**
     * 查询好友关系列表
     * 
     * @param chatUserRelation 好友关系
     * @return 好友关系集合
     */
    public List<ChatUserRelation> selectChatUserRelationList(ChatUserRelation chatUserRelation);

    public List<ChatUserRelationVo> selectChatUserRelationVoList(ChatUserRelationVo chatUserRelationvo);

    /**
     * 新增好友关系
     * 
     * @param chatUserRelation 好友关系
     * @return 结果
     */
    public int insertChatUserRelation(ChatUserRelation chatUserRelation);

    /**
     * 修改好友关系
     * 
     * @param chatUserRelation 好友关系
     * @return 结果
     */
    public int updateChatUserRelation(ChatUserRelation chatUserRelation);

    /**
     * 批量删除好友关系
     * 
     * @param ids 需要删除的好友关系主键集合
     * @return 结果
     */
    public int deleteChatUserRelationByIds(String[] ids);

    /**
     * 删除好友关系信息
     * 
     * @param id 好友关系主键
     * @return 结果
     */
    public int deleteChatUserRelationById(String id);

    public int addFriend(ChatUserRelationDto chatUserRelationDto);

}
