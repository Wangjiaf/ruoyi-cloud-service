package com.ruoyi.chat.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.chat.domain.ChatUserRelation;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.system.api.domain.SysUser;

public class ChatGroupMessageVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 删除标识(0代表存在 1代表删除) */
    private String delFlag;

    /** 群号 */
    @Excel(name = "群号")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long groupId;

    /** 发送者用户ID */
    @Excel(name = "发送者用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long fromUserId;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String messageContent;

    /** 撤回标识(0代表未撤回 1代表撤回) */
    @Excel(name = "撤回标识(0代表未撤回 1代表撤回)")
    private String revokeFlag;

    private ChatUserRelation fromUserRelation;

    private SysUser fromUser;

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getRevokeFlag() {
        return revokeFlag;
    }

    public void setRevokeFlag(String revokeFlag) {
        this.revokeFlag = revokeFlag;
    }

    public ChatUserRelation getFromUserRelation() {
        return fromUserRelation;
    }

    public void setFromUserRelation(ChatUserRelation fromUserRelation) {
        this.fromUserRelation = fromUserRelation;
    }

    public SysUser getFromUser() {
        return fromUser;
    }

    public void setFromUser(SysUser fromUser) {
        this.fromUser = fromUser;
    }

}
