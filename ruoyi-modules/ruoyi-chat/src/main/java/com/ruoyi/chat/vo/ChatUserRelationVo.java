package com.ruoyi.chat.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.system.api.domain.SysUser;

/**
 * @author
 */
public class ChatUserRelationVo extends BaseEntity {

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

    /** 用户 */
    private SysUser user;

    /** 好友用户 */
    private SysUser relationUser;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRelationUserId() {
        return relationUserId;
    }

    public void setRelationUserId(Long relationUserId) {
        this.relationUserId = relationUserId;
    }

    public String getRelationUserRemark() {
        return relationUserRemark;
    }

    public void setRelationUserRemark(String relationUserRemark) {
        this.relationUserRemark = relationUserRemark;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysUser getRelationUser() {
        return relationUser;
    }

    public void setRelationUser(SysUser relationUser) {
        this.relationUser = relationUser;
    }

}
