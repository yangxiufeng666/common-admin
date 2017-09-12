package com.common.system.service.impl;

import com.common.system.entity.RcMenu;
import com.common.system.entity.TreeGridNode;
import com.common.system.service.MenuService;
import com.common.system.service.TreeGridService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/9/12.
 * Time:17:41
 * ProjectName:Common-admin
 */
@Service
public class TreeGridServiceImpl implements TreeGridService {
    private static final Log LOG = LogFactory.getLog(TreeGridServiceImpl.class);

    @Autowired
    private MenuService menuService;
    @Override
    public List<TreeGridNode> getMenuTreeGridNodes() {
        List<RcMenu> list = menuService.getMenu();
        List<TreeGridNode> treeGridNodeList = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (RcMenu menu:list
                 ) {
                TreeGridNode treeNode = new TreeGridNode();
                treeNode.setId(menu.getId());
                treeNode.setName(menu.getName());
                treeNode.setUrl(menu.getUrl());
                treeNode.set_parentId(menu.getpId());
                treeNode.setCreateDate(menu.getCreateTime());
                treeGridNodeList.add(treeNode);
            }
        }
        return treeGridNodeList;
    }
}
