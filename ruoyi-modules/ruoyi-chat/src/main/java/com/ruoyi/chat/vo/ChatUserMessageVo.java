package com.ruoyi.chat.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.system.api.domain.SysUser;

public class ChatUserMessageVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 删除标识(0代表存在 1代表删除) */
    private String delFlag;

    /** 发送者用户ID */
    @Excel(name = "发送者用户ID")
    private Long fromUserId;

    /** 接收者用户ID */
    @Excel(name = "接收者用户ID")
    private Long toUserId;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String messageContent;

    /** 撤回标识(0代表存在 1代表撤回) */
    @Excel(name = "撤回标识(0代表存在 1代表撤回)")
    private String revokeFlag;

    /** 发送者删除标识(0代表存在 1代表删除) */
    @Excel(name = "发送者删除标识(0代表存在 1代表删除)")
    private String fromUserDelFlag;

    /** 接收者删除标识(0代表存在 1代表删除) */
    @Excel(name = "接收者删除标识(0代表存在 1代表删除)")
    private String toUserDelFlag;

    /** 发送者用户 */
    private SysUser fromUser;

    /** 接收者用户 */
    private SysUser toUser;

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

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
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

    public String getFromUserDelFlag() {
        return fromUserDelFlag;
    }

    public void setFromUserDelFlag(String fromUserDelFlag) {
        this.fromUserDelFlag = fromUserDelFlag;
    }

    public String getToUserDelFlag() {
        return toUserDelFlag;
    }

    public void setToUserDelFlag(String toUserDelFlag) {
        this.toUserDelFlag = toUserDelFlag;
    }

    public SysUser getFromUser() {
        return fromUser;
    }

    public void setFromUser(SysUser fromUser) {
        this.fromUser = fromUser;
    }

    public SysUser getToUser() {
        return toUser;
    }

    public void setToUser(SysUser toUser) {
        this.toUser = toUser;
    }

}
