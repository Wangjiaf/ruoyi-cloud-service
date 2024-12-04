package com.ruoyi.chat.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 好友聊天消息对象 chat_user_message
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
public class ChatUserMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 删除标识(0代表存在 1代表删除) */
    private String delFlag;

    /** 发送者用户ID */
    @Excel(name = "发送者用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long fromUserId;

    /** 接收者用户ID */
    @Excel(name = "接收者用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
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

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setFromUserId(Long fromUserId) 
    {
        this.fromUserId = fromUserId;
    }

    public Long getFromUserId() 
    {
        return fromUserId;
    }
    public void setToUserId(Long toUserId) 
    {
        this.toUserId = toUserId;
    }

    public Long getToUserId() 
    {
        return toUserId;
    }
    public void setMessageContent(String messageContent) 
    {
        this.messageContent = messageContent;
    }

    public String getMessageContent() 
    {
        return messageContent;
    }
    public void setRevokeFlag(String revokeFlag) 
    {
        this.revokeFlag = revokeFlag;
    }

    public String getRevokeFlag() 
    {
        return revokeFlag;
    }
    public void setFromUserDelFlag(String fromUserDelFlag) 
    {
        this.fromUserDelFlag = fromUserDelFlag;
    }

    public String getFromUserDelFlag() 
    {
        return fromUserDelFlag;
    }
    public void setToUserDelFlag(String toUserDelFlag) 
    {
        this.toUserDelFlag = toUserDelFlag;
    }

    public String getToUserDelFlag() 
    {
        return toUserDelFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("fromUserId", getFromUserId())
            .append("toUserId", getToUserId())
            .append("messageContent", getMessageContent())
            .append("revokeFlag", getRevokeFlag())
            .append("fromUserDelFlag", getFromUserDelFlag())
            .append("toUserDelFlag", getToUserDelFlag())
            .toString();
    }
}
