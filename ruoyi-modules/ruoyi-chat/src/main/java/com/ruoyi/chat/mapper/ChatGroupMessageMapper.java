package com.ruoyi.chat.mapper;

import java.util.List;
import com.ruoyi.chat.domain.ChatGroupMessage;
import com.ruoyi.chat.vo.ChatGroupMessageVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群消息Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-02
 */
@Mapper
public interface ChatGroupMessageMapper
{
    /**
     * 查询群消息
     *
     * @param id 群消息主键
     * @return 群消息
     */
    public ChatGroupMessage selectChatGroupMessageById(String id);

    /**
     * 查询群消息列表
     *
     * @param chatGroupMessage 群消息
     * @return 群消息集合
     */
    public List<ChatGroupMessage> selectChatGroupMessageList(ChatGroupMessage chatGroupMessage);

    /**
     * 新增群消息
     *
     * @param chatGroupMessage 群消息
     * @return 结果
     */
    public int insertChatGroupMessage(ChatGroupMessage chatGroupMessage);

    /**
     * 修改群消息
     *
     * @param chatGroupMessage 群消息
     * @return 结果
     */
    public int updateChatGroupMessage(ChatGroupMessage chatGroupMessage);

    /**
     * 删除群消息
     *
     * @param id 群消息主键
     * @return 结果
     */
    public int deleteChatGroupMessageById(String id);

    /**
     * 批量删除群消息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChatGroupMessageByIds(String[] ids);

    public List<ChatGroupMessageVo> selectChatGroupMessageVoList(ChatGroupMessageVo chatGroupMessageVo);

}
