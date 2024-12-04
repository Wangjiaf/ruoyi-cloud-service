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
import com.ruoyi.chat.domain.ChatGroup;
import com.ruoyi.chat.service.IChatGroupService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 群组主数据Controller
 *
 * @author ruoyi
 * @date 2024-12-02
 */
@RestController
@RequestMapping("/chatGroup")
public class ChatGroupController extends BaseController
{

    @Autowired
    private IChatGroupService chatGroupService;

    /**
     * 查询群组主数据列表
     */
//    @RequiresPermissions("chat:chatGroup:list")
    @GetMapping("/list")
    public TableDataInfo list(ChatGroup chatGroup)
    {
        startPage();
        List<ChatGroup> list = chatGroupService.selectChatGroupList(chatGroup);
        return getDataTable(list);
    }

    /**
     * 导出群组主数据列表
     */
    @RequiresPermissions("chat:chatGroup:export")
    @Log(title = "群组主数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChatGroup chatGroup)
    {
        List<ChatGroup> list = chatGroupService.selectChatGroupList(chatGroup);
        ExcelUtil<ChatGroup> util = new ExcelUtil<ChatGroup>(ChatGroup.class);
        util.exportExcel(response, list, "群组主数据数据");
    }

    /**
     * 获取群组主数据详细信息
     */
    @RequiresPermissions("chat:chatGroup:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(chatGroupService.selectChatGroupById(id));
    }

    /**
     * 新增群组主数据
     */
    @RequiresPermissions("chat:chatGroup:add")
    @Log(title = "群组主数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatGroup chatGroup)
    {
        return toAjax(chatGroupService.insertChatGroup(chatGroup));
    }

    /**
     * 修改群组主数据
     */
    @RequiresPermissions("chat:chatGroup:edit")
    @Log(title = "群组主数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatGroup chatGroup)
    {
        return toAjax(chatGroupService.updateChatGroup(chatGroup));
    }

    /**
     * 删除群组主数据
     */
    @RequiresPermissions("chat:chatGroup:remove")
    @Log(title = "群组主数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(chatGroupService.deleteChatGroupByIds(ids));
    }

    /**
     * 创建新群组
     */
    @Log(title = "群组主数据", businessType = BusinessType.INSERT)
    @PostMapping("/addGroup")
    public AjaxResult addGroup(@RequestBody ChatGroup chatGroup)
    {
        return chatGroupService.addGroup(chatGroup);
    }

}
