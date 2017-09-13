package com.common.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/9/12.
 * Time:18:00
 * ProjectName:Common-admin
 */
public class TreeGridWrapper implements Serializable {
    private static final long serialVersionUID = 3871928415548297855L;

    private Integer total;

    private List<TreeGridNode> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<TreeGridNode> getRows() {
        return rows;
    }

    public void setRows(List<TreeGridNode> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "TreeGridWrapper{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
