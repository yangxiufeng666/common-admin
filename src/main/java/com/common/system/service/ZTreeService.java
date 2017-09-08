package com.common.system.service;

import com.common.system.entity.ZTreeNode;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/8/7.
 * Time:14:03
 * ProjectName:Common-admin
 */
public interface ZTreeService {
    List<ZTreeNode> getMenuZTreeNodes();
    String buildZTree( List<ZTreeNode> zTreeNodeList);
}
