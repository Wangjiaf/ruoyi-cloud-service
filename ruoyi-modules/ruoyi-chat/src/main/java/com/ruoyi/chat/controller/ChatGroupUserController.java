package com.ruoyi.chat.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.chat.dto.ChatGroupUserDto;
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
import com.ruoyi.chat.domain.ChatGroupUser;
import com.ruoyi.chat.service.IChatGroupUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 群成员数据Controller
 *
 * @author ruoyi
 * @date 2024-12-02
 */
@RestController
@RequestMapping("/chatGroupUser")
public class ChatGroupUserController extends BaseController
{

    @Autowired
    private IChatGroupUserService chatGroupUserService;

    /**
     * 查询群成员数据列表
     */
    @RequiresPermissions("chat:chatGroupUser:list")
    @GetMapping("/list")
    public TableDataInfo list(ChatGroupUser chatGroupUser)
    {
        startPage();
        List<ChatGroupUser> list = chatGroupUserService.selectChatGroupUserList(chatGroupUser);
        return getDataTable(list);
    }

    /**
     * 导出群成员数据列表
     */
    @RequiresPermissions("chat:chatGroupUser:export")
    @Log(title = "群成员数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChatGroupUser chatGroupUser)
    {
        List<ChatGroupUser> list = chatGroupUserService.selectChatGroupUserList(chatGroupUser);
        ExcelUtil<ChatGroupUser> util = new ExcelUtil<ChatGroupUser>(ChatGroupUser.class);
        util.exportExcel(response, list, "群成员数据数据");
    }

    /**
     * 获取群成员数据详细信息
     */
    @RequiresPermissions("chat:chatGroupUser:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(chatGroupUserService.selectChatGroupUserById(id));
    }

    /**
     * 新增群成员数据
     */
    @RequiresPermissions("chat:chatGroupUser:add")
    @Log(title = "群成员数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatGroupUser chatGroupUser)
    {
        return toAjax(chatGroupUserService.insertChatGroupUser(chatGroupUser));
    }

    /**
     * 修改群成员数据
     */
    @RequiresPermissions("chat:chatGroupUser:edit")
    @Log(title = "群成员数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatGroupUser chatGroupUser)
    {
        return toAjax(chatGroupUserService.updateChatGroupUser(chatGroupUser));
    }

    /**
     * 删除群成员数据
     */
    @RequiresPermissions("chat:chatGroupUser:remove")
    @Log(title = "群成员数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(chatGroupUserService.deleteChatGroupUserByIds(ids));
    }

    @Log(title = "群成员数据", businessType = BusinessType.INSERT)
    @PostMapping("/addGroupUser")
    public AjaxResult addGroupUser(@RequestBody ChatGroupUserDto chatGroupUserDto)
    {
        return toAjax(chatGroupUserService.addGroupUser(chatGroupUserDto));
    }

}
