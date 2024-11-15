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
import com.ruoyi.chat.domain.ChatUserRelation;
import com.ruoyi.chat.service.IChatUserRelationService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 好友关系Controller
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/chatUserRelation")
public class ChatUserRelationController extends BaseController
{
    @Autowired
    private IChatUserRelationService chatUserRelationService;

    /**
     * 查询好友关系列表
     */
    @RequiresPermissions("chat:chatUserRelation:list")
    @GetMapping("/list")
    public TableDataInfo list(ChatUserRelation chatUserRelation)
    {
        startPage();
        List<ChatUserRelation> list = chatUserRelationService.selectChatUserRelationList(chatUserRelation);
        return getDataTable(list);
    }

    /**
     * 导出好友关系列表
     */
    @RequiresPermissions("chat:chatUserRelation:export")
    @Log(title = "好友关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChatUserRelation chatUserRelation)
    {
        List<ChatUserRelation> list = chatUserRelationService.selectChatUserRelationList(chatUserRelation);
        ExcelUtil<ChatUserRelation> util = new ExcelUtil<ChatUserRelation>(ChatUserRelation.class);
        util.exportExcel(response, list, "好友关系数据");
    }

    /**
     * 获取好友关系详细信息
     */
    @RequiresPermissions("chat:chatUserRelation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(chatUserRelationService.selectChatUserRelationById(id));
    }

    /**
     * 新增好友关系
     */
    @RequiresPermissions("chat:chatUserRelation:add")
    @Log(title = "好友关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatUserRelation chatUserRelation)
    {
        return toAjax(chatUserRelationService.insertChatUserRelation(chatUserRelation));
    }

    /**
     * 修改好友关系
     */
    @RequiresPermissions("chat:chatUserRelation:edit")
    @Log(title = "好友关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatUserRelation chatUserRelation)
    {
        return toAjax(chatUserRelationService.updateChatUserRelation(chatUserRelation));
    }

    /**
     * 删除好友关系
     */
    @RequiresPermissions("chat:chatUserRelation:remove")
    @Log(title = "好友关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(chatUserRelationService.deleteChatUserRelationByIds(ids));
    }
}
