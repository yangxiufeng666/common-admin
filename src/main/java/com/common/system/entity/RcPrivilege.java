package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

public class RcPrivilege implements Serializable{
    private static final long serialVersionUID = -7117576879637960180L;
    private Integer roleId;

    private String menuId;

    private Date createTime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}