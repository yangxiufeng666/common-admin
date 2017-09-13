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
    /**
     * TreeGrid id 和Parent不能是String,也是蛋疼
     */
    private Long id;

    private String name;

    private String url;

    private Long _parentId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String state = "open";

    private String menuId;

    private Integer level;

    private Integer sort;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long get_parentId() {
        return _parentId;
    }

    public void set_parentId(Long _parentId) {
        this._parentId = _parentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
