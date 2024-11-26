package com.ruoyi.chat.service;

import java.util.List;
import com.ruoyi.chat.domain.ChatTip;
import com.ruoyi.chat.vo.ChatTipVo;

/**
 * 消息提示Service接口
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
public interface IChatTipService 
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
     * 批量删除消息提示
     * 
     * @param ids 需要删除的消息提示主键集合
     * @return 结果
     */
    public int deleteChatTipByIds(String[] ids);

    /**
     * 删除消息提示信息
     * 
     * @param id 消息提示主键
     * @return 结果
     */
    public int deleteChatTipById(String id);

    public List<ChatTipVo> selectChatTipVoList(ChatTipVo chatTipVo);

    public int resetChatTipCount(String id);

}
