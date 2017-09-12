package com.common.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mr.Yangxiufeng on 2017/9/12.
 * Time:15:46
 * ProjectName:Common-admin
 */
public class TreeGridNode  implements Serializable{
    private static final long serialVersionUID = 7333189210968942514L;

    private String id;

    private String name;

    private String url;

    private String _parentId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
