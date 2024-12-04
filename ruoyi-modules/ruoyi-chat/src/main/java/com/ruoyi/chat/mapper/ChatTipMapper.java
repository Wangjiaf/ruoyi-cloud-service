package com.ruoyi.chat.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.chat.domain.ChatTip;
import com.ruoyi.chat.vo.ChatTipVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消息提示Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@Mapper
public interface ChatTipMapper 
{
    /**
     * 查询消息提示
     * 
     * @param id 消息提示主键
     * @return 消息提示
     */
    public ChatTip selectChatTipById(String id);

    /**
     * 查询消息提示列表
     * 
     * @param chatTip 消息提示
     * @return 消息提示集合
     */
    public List<ChatTip> selectChatTipList(ChatTip chatTip);

    /**
     * 新增消息提示
     * 
     * @param chatTip 消息提示
     * @return 结果
     */
    public int insertChatTip(ChatTip chatTip);

    /**
     * 修改消息提示
     * 
     * @param chatTip 消息提示
     * @return 结果
     */
    public int updateChatTip(ChatTip chatTip);

    /**
     * 删除消息提示
     * 
     * @param id 消息提示主键
     * @return 结果
     */
    public int deleteChatTipById(String id);

    /**
     * 批量删除消息提示
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChatTipByIds(String[] ids);

    public List<ChatTipVo> selectChatTipVoList(ChatTipVo chatTipVo);

    public ChatTip selectChatTipBy_userId_tipFromId(@Param("chatType") String chatType, @Param("userId") Long userId, @Param("tipFromId") Long tipFromId);

    public int resetChatTipCount(@Param("id") String id, @Param("updateBy") Long updateBy,  @Param("updateTime") Date updateTime);

}
