package com.ruoyi.chat.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 群成员数据对象 chat_group_user
 *
 * @author ruoyi
 * @date 2024-12-02
 */
public class ChatGroupUser extends BaseEntity
{

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 删除标识(0代表存在 1代表删除) */
    private String delFlag;

    /** 群号 */
    @Excel(name = "群号")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long groupId;

    /** 群成员用户ID */
    @Excel(name = "群成员用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /** 成员在群内角色 */
    @Excel(name = "成员在群内角色")
    private String groupUserRole;

    /** 成员离群标识(0代表未离群 1代表离群) */
    @Excel(name = "成员离群标识(0代表未离群 1代表离群)")
    private String leaveFlag;

    /** 成员离群时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成员离群时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveTime;

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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setGroupUserRole(String groupUserRole)
    {
        this.groupUserRole = groupUserRole;
    }

    public String getGroupUserRole()
    {
        return groupUserRole;
    }
    public void setLeaveFlag(String leaveFlag)
    {
        this.leaveFlag = leaveFlag;
    }

    public String getLeaveFlag()
    {
        return leaveFlag;
    }
    public void setLeaveTime(Date leaveTime)
    {
        this.leaveTime = leaveTime;
    }

    public Date getLeaveTime()
    {
        return leaveTime;
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
                .append("userId", getUserId())
                .append("groupUserRole", getGroupUserRole())
                .append("leaveFlag", getLeaveFlag())
                .append("leaveTime", getLeaveTime())
                .toString();
    }
}
