package com.ruoyi.chat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 消息提示对象 chat_tip
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
public class ChatTip extends BaseEntity
{
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
    private Long userId;

    /** 好友ID/群ID */
    @Excel(name = "好友ID/群ID")
    private Long tipFromId;

    /** 提示内容 */
    @Excel(name = "提示内容")
    private String tipContent;

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
    public void setChatType(String chatType) 
    {
        this.chatType = chatType;
    }

    public String getChatType() 
    {
        return chatType;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTipFromId(Long tipFromId) 
    {
        this.tipFromId = tipFromId;
    }

    public Long getTipFromId() 
    {
        return tipFromId;
    }
    public void setTipContent(String tipContent) 
    {
        this.tipContent = tipContent;
    }

    public String getTipContent() 
    {
        return tipContent;
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
            .append("chatType", getChatType())
            .append("userId", getUserId())
            .append("tipFromId", getTipFromId())
            .append("tipContent", getTipContent())
            .toString();
    }
}
