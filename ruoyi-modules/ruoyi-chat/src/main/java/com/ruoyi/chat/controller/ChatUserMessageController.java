package com.ruoyi.chat.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.chat.domain.ChatUserMessage;
import com.ruoyi.chat.service.IChatUserMessageService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 好友聊天消息Controller
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/chatUserMessage")
public class ChatUserMessageController extends BaseController
{
    @Autowired
    private IChatUserMessageService chatUserMessageService;

    /**
     * 查询好友聊天消息列表
     */
    @RequiresPermissions("chat:chatUserMessage:list")
    @GetMapping("/list")
    public TableDataInfo list(ChatUserMessage chatUserMessage)
    {
        startPage();
        List<ChatUserMessage> list = chatUserMessageService.selectChatUserMessageList(chatUserMessage);
        return getDataTable(list);
    }

    /**
     * 导出好友聊天消息列表
     */
    @RequiresPermissions("chat:chatUserMessage:export")
    @Log(title = "好友聊天消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChatUserMessage chatUserMessage)
    {
        List<ChatUserMessage> list = chatUserMessageService.selectChatUserMessageList(chatUserMessage);
        ExcelUtil<ChatUserMessage> util = new ExcelUtil<ChatUserMessage>(ChatUserMessage.class);
        util.exportExcel(response, list, "好友聊天消息数据");
    }

    /**
     * 获取好友聊天消息详细信息
     */
    @RequiresPermissions("chat:chatUserMessage:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(chatUserMessageService.selectChatUserMessageById(id));
    }

    /**
     * 新增好友聊天消息
     */
    @RequiresPermissions("chat:chatUserMessage:add")
    @Log(title = "好友聊天消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatUserMessage chatUserMessage)
    {
        return toAjax(chatUserMessageService.insertChatUserMessage(chatUserMessage));
    }

    /**
     * 修改好友聊天消息
     */
    @RequiresPermissions("chat:chatUserMessage:edit")
    @Log(title = "好友聊天消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatUserMessage chatUserMessage)
    {
        return toAjax(chatUserMessageService.updateChatUserMessage(chatUserMessage));
    }

    /**
     * 删除好友聊天消息
     */
    @RequiresPermissions("chat:chatUserMessage:remove")
    @Log(title = "好友聊天消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(chatUserMessageService.deleteChatUserMessageByIds(ids));
    }
}
