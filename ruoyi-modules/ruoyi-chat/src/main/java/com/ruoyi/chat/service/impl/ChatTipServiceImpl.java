package com.ruoyi.chat.service.impl;

import java.util.List;

import com.ruoyi.chat.utils.ChatTipUtils;
import com.ruoyi.chat.vo.ChatTipVo;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.chat.mapper.ChatTipMapper;
import com.ruoyi.chat.domain.ChatTip;
import com.ruoyi.chat.service.IChatTipService;

/**
 * 消息提示Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@Service
public class ChatTipServiceImpl implements IChatTipService 
{
    @Autowired
    private ChatTipMapper chatTipMapper;

    /**
     * 查询消息提示
     * 
     * @param id 消息提示主键
     * @return 消息提示
     */
    @Override
    public ChatTip selectChatTipById(String id)
    {
        return chatTipMapper.selectChatTipById(id);
    }

    /**
     * 查询消息提示列表
     * 
     * @param chatTip 消息提示
     * @return 消息提示
     */
    @Override
    public List<ChatTip> selectChatTipList(ChatTip chatTip)
    {
        return chatTipMapper.selectChatTipList(chatTip);
    }

    /**
     * 新增消息提示
     * 
     * @param chatTip 消息提示
     * @return 结果
     */
    @Override
    public int insertChatTip(ChatTip chatTip)
    {
        chatTip.setCreateTime(DateUtils.getNowDate());
        return chatTipMapper.insertChatTip(chatTip);
    }

    /**
     * 修改消息提示
     * 
     * @param chatTip 消息提示
     * @return 结果
     */
    @Override
    public int updateChatTip(ChatTip chatTip)
    {
        chatTip.setUpdateTime(DateUtils.getNowDate());
        return chatTipMapper.updateChatTip(chatTip);
    }

    /**
     * 批量删除消息提示
     * 
     * @param ids 需要删除的消息提示主键
     * @return 结果
     */
    @Override
    public int deleteChatTipByIds(String[] ids)
    {
        return chatTipMapper.deleteChatTipByIds(ids);
    }

    /**
     * 删除消息提示信息
     * 
     * @param id 消息提示主键
     * @return 结果
     */
    @Override
    public int deleteChatTipById(String id)
    {
        return chatTipMapper.deleteChatTipById(id);
    }

    @Override
    public List<ChatTipVo> selectChatTipVoList(ChatTipVo chatTipVo) {
        List<ChatTipVo> list = chatTipMapper.selectChatTipVoList(chatTipVo);
        list.forEach(item -> {
            item.setTipContent(ChatTipUtils.fullTipContent(item.getTipContent()));
        });
        return list;
    }

    @Override
    public int resetChatTipCount(String id) {
        return chatTipMapper.resetChatTipCount(id, SecurityUtils.getUserId(), DateUtils.getNowDate());
    }

}
