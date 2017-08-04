package com.common.system.entity;

import java.io.Serializable;

/**
 * Created by Mr.Yangxiufeng on 2017/8/2.
 * Time:17:52
 * ProjectName:Common-admin
 */
public class ZTreeNode implements Serializable{
    private static final long serialVersionUID = -7664784184286632837L;

    private String id;
    private String pId;
    private String name;
    private boolean open = true;//是否打开节点

    private boolean checked;//是否被选中

    private String code;

    private Integer level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
