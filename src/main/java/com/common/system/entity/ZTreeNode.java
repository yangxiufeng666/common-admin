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
    private boolean open;//是否打开节点

    private boolean checked;//是否被选中

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
}
