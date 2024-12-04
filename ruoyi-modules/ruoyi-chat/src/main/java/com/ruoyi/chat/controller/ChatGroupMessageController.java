package com.ruoyi.chat.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.chat.vo.ChatGroupMessageVo;
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
import com.ruoyi.chat.domain.ChatGroupMessage;
import com.ruoyi.chat.service.IChatGroupMessageService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 群消息Controller
 *
 * @author ruoyi
 * @date 2024-12-02
 */
@RestController
@RequestMapping("/chatGroupMessage")
public class ChatGroupMessageController extends BaseController
{

    @Autowired
    private IChatGroupMessageService chatGroupMessageService;

    /**
     * 查询群消息列表
     */
    @RequiresPermissions("chat:chatGroupMessage:list")
    @GetMapping("/list")
    public TableDataInfo list(ChatGroupMessage chatGroupMessage)
    {
        startPage();
        List<ChatGroupMessage> list = chatGroupMessageService.selectChatGroupMessageList(chatGroupMessage);
        return getDataTable(list);
    }

    /**
     * 导出群消息列表
     */
    @RequiresPermissions("chat:chatGroupMessage:export")
    @Log(title = "群消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChatGroupMessage chatGroupMessage)
    {
        List<ChatGroupMessage> list = chatGroupMessageService.selectChatGroupMessageList(chatGroupMessage);
        ExcelUtil<ChatGroupMessage> util = new ExcelUtil<ChatGroupMessage>(ChatGroupMessage.class);
        util.exportExcel(response, list, "群成员数据数据");
    }

    /**
     * 获取群消息详细信息
     */
    @RequiresPermissions("chat:chatGroupMessage:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(chatGroupMessageService.selectChatGroupMessageById(id));
    }

    /**
     * 新增群消息
     */
    @RequiresPermissions("chat:chatGroupMessage:add")
    @Log(title = "群消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatGroupMessage chatGroupMessage)
    {
        return toAjax(chatGroupMessageService.insertChatGroupMessage(chatGroupMessage));
    }

    /**
     * 修改群消息
     */
    @RequiresPermissions("chat:chatGroupMessage:edit")
    @Log(title = "群消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatGroupMessage chatGroupMessage)
    {
        return toAjax(chatGroupMessageService.updateChatGroupMessage(chatGroupMessage));
    }

    /**
     * 删除群消息
     */
    @RequiresPermissions("chat:chatGroupMessage:remove")
    @Log(title = "群消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(chatGroupMessageService.deleteChatGroupMessageByIds(ids));
    }

    @GetMapping("/listVo")
    public TableDataInfo listVo(ChatGroupMessageVo chatGroupMessageVo)
    {
        startPage();
        List<ChatGroupMessageVo> list = chatGroupMessageService.selectChatGroupMessageVoList(chatGroupMessageVo);
        return getDataTable(list);
    }

    @Log(title = "发送群消息", businessType = BusinessType.INSERT)
    @PostMapping("/sendChatGroupMessage")
    public AjaxResult sendChatGroupMessage(@RequestBody ChatGroupMessage chatGroupMessage)
    {
        return toAjax(chatGroupMessageService.sendChatGroupMessage(chatGroupMessage));
    }

}
