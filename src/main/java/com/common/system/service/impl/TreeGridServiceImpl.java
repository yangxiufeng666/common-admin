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
import java.util.Comparator;
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
        if (list != null && list.size() > 0) {
            for (RcMenu menu : list
                    ) {
                TreeGridNode treeNode = new TreeGridNode();
                treeNode.setId(Long.valueOf(menu.getId()));
                treeNode.setName(menu.getName());
                treeNode.setUrl(menu.getUrl());
                if (menu.getpId().equals("0")) {
                    treeNode.set_parentId(null);
                } else {
                    treeNode.set_parentId(Long.valueOf(menu.getpId()));
                }
                treeNode.setMenuId(menu.getId());
                treeNode.setLevel(menu.getLevel());
                treeNode.setSort(menu.getSort());
                treeNode.setCode(menu.getCode());
                treeNode.setCreateDate(menu.getCreateTime());
                treeGridNodeList.add(treeNode);

            }
            treeGridNodeList.sort(new Comparator<TreeGridNode>() {
                @Override
                public int compare(TreeGridNode o1, TreeGridNode o2) {
                    if (o1.getSort()==o2.getSort()){
                        return 0;
                    }
                    if (o1.getSort() > o2.getSort()){
                        return 1;
                    }
                    return -1;
                }
            });
        }
        return treeGridNodeList;
    }
}
