package com.ruoyi.chat.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 群组主数据对象 chat_group
 *
 * @author ruoyi
 * @date 2024-12-02
 */
public class ChatGroup extends BaseEntity
{

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 删除标识(0代表存在 1代表删除) */
    private String delFlag;

    /** 群号 */
    @Excel(name = "群号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long groupId;

    /** 群名称 */
    @Excel(name = "群名称")
    private String groupName;

    /** 群头像 */
    @Excel(name = "群头像")
    private String avatar;

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
    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    public Long getGroupId()
    {
        return groupId;
    }
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupName()
    {
        return groupName;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
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
                .append("groupId", getGroupId())
                .append("groupName", getGroupName())
                .append("avatar", getAvatar())
                .toString();
    }
}