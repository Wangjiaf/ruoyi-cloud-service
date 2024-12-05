package com.ruoyi.chat.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.web.domain.BaseEntity;

public class ChatGroupVo extends BaseEntity
{

    /** 主键ID */
    private String id;

    /** 删除标识(0代表存在 1代表删除) */
    private String delFlag;

    /** 群号 */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long groupId;

    /** 群名称 */
    private String groupName;

    /** 群头像 */
    private String avatar;

    /** 群成员数 */
    private String groupUserCount;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGroupUserCount() {
        return groupUserCount;
    }

    public void setGroupUserCount(String groupUserCount) {
        this.groupUserCount = groupUserCount;
    }

}
