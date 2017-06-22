package com.common.system.entity;

import java.util.Date;

public class RcPermission {
    private Integer id;

    private String statusId;

    private Date createTime;

    private Date updateTime;

    private String permissionsName;

    private String permissionsValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName == null ? null : permissionsName.trim();
    }

    public String getPermissionsValue() {
        return permissionsValue;
    }

    public void setPermissionsValue(String permissionsValue) {
        this.permissionsValue = permissionsValue == null ? null : permissionsValue.trim();
    }
}