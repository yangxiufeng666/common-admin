package com.common.system.service;

import com.common.system.entity.TreeGridNode;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/9/12.
 * Time:17:40
 * ProjectName:Common-admin
 */
public interface TreeGridService {
    List<TreeGridNode> getMenuTreeGridNodes();
}
