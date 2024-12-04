package com.ruoyi.chat.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 好友关系对象 chat_user_relation
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
public class ChatUserRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 删除标识(0代表存在 1代表删除) */
    private String delFlag;

    /** 用户ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /** 好友用户ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long relationUserId;

    /** 好友备注 */
    private String relationUserRemark;

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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRelationUserId(Long relationUserId) 
    {
        this.relationUserId = relationUserId;
    }

    public Long getRelationUserId() 
    {
        return relationUserId;
    }
    public void setRelationUserRemark(String relationUserRemark) 
    {
        this.relationUserRemark = relationUserRemark;
    }

    public String getRelationUserRemark() 
    {
        return relationUserRemark;
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
            .append("userId", getUserId())
            .append("relationUserId", getRelationUserId())
            .append("relationUserRemark", getRelationUserRemark())
            .toString();
    }
}
