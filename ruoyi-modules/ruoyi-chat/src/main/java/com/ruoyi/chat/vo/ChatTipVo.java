package com.ruoyi.chat.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.chat.domain.ChatGroup;
import com.ruoyi.chat.domain.ChatUserRelation;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.system.api.domain.SysUser;

public class ChatTipVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 删除标识(0代表存在 1代表删除) */
    private String delFlag;

    /** 聊天方式 */
    @Excel(name = "聊天方式")
    private String chatType;

    /** 用户ID */
    @Excel(name = "用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /** 好友ID/群ID */
    @Excel(name = "好友ID/群ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long tipFromId;

    /** 提示内容 */
    @Excel(name = "提示内容")
    private String tipContent;

    /** 未读消息数量 */
    @Excel(name = "未读消息数量")
    private Integer unReadCount;

    private ChatUserRelation tipFromChatUserRelation;

    private SysUser tipFromUser;

    private ChatGroup tipFromChatGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTipFromId() {
        return tipFromId;
    }

    public void setTipFromId(Long tipFromId) {
        this.tipFromId = tipFromId;
    }

    public String getTipContent() {
        return tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    public ChatUserRelation getTipFromChatUserRelation() {
        return tipFromChatUserRelation;
    }

    public void setTipFromChatUserRelation(ChatUserRelation tipFromChatUserRelation) {
        this.tipFromChatUserRelation = tipFromChatUserRelation;
    }

    public SysUser getTipFromUser() {
        return tipFromUser;
    }

    public void setTipFromUser(SysUser tipFromUser) {
        this.tipFromUser = tipFromUser;
    }

    public Integer getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }

    public ChatGroup getTipFromChatGroup() {
        return tipFromChatGroup;
    }

    public void setTipFromChatGroup(ChatGroup tipFromChatGroup) {
        this.tipFromChatGroup = tipFromChatGroup;
    }

}
