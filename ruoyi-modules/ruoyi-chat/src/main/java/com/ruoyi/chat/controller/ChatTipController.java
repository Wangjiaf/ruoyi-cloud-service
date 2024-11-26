package com.ruoyi.chat.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.chat.vo.ChatTipVo;
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
import com.ruoyi.chat.domain.ChatTip;
import com.ruoyi.chat.service.IChatTipService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 消息提示Controller
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/chatTip")
public class ChatTipController extends BaseController
{
    @Autowired
    private IChatTipService chatTipService;

    /**
     * 查询消息提示列表
     */
    @RequiresPermissions("chat:chatTip:list")
    @GetMapping("/list")
    public TableDataInfo list(ChatTip chatTip)
    {
        startPage();
        List<ChatTip> list = chatTipService.selectChatTipList(chatTip);
        return getDataTable(list);
    }

    @GetMapping("/listVo")
    public TableDataInfo listVo(ChatTipVo chatTipVo)
    {
        startPage();
        List<ChatTipVo> list = chatTipService.selectChatTipVoList(chatTipVo);
        return getDataTable(list);
    }

    /**
     * 导出消息提示列表
     */
    @RequiresPermissions("chat:chatTip:export")
    @Log(title = "消息提示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChatTip chatTip)
    {
        List<ChatTip> list = chatTipService.selectChatTipList(chatTip);
        ExcelUtil<ChatTip> util = new ExcelUtil<ChatTip>(ChatTip.class);
        util.exportExcel(response, list, "消息提示数据");
    }

    /**
     * 获取消息提示详细信息
     */
    @RequiresPermissions("chat:chatTip:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(chatTipService.selectChatTipById(id));
    }

    /**
     * 新增消息提示
     */
    @RequiresPermissions("chat:chatTip:add")
    @Log(title = "消息提示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatTip chatTip)
    {
        return toAjax(chatTipService.insertChatTip(chatTip));
    }

    /**
     * 修改消息提示
     */
    @RequiresPermissions("chat:chatTip:edit")
    @Log(title = "消息提示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatTip chatTip)
    {
        return toAjax(chatTipService.updateChatTip(chatTip));
    }

    /**
     * 删除消息提示
     */
    @RequiresPermissions("chat:chatTip:remove")
    @Log(title = "消息提示", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(chatTipService.deleteChatTipByIds(ids));
    }

    @GetMapping(value = "/resetChatTipCount/{id}")
    @Log(title = "重置消息", businessType = BusinessType.UPDATE)
    public AjaxResult resetChatTipCount(@PathVariable("id") String id)
    {
        return toAjax(chatTipService.resetChatTipCount(id));
    }

}
