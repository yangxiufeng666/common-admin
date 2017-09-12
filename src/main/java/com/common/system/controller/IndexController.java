package com.common.system.controller;

import com.common.system.entity.RcMenu;
import com.common.system.entity.RcPrivilege;
import com.common.system.service.MenuService;
import com.common.system.service.PrivilegeService;
import com.common.system.shiro.ShiroKit;
import com.common.system.shiro.ShiroUser;
import com.common.system.util.MenuComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/20.
 * Time:17:42
 * ProjectName:Common-admin
 */
@RestController
public class IndexController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = {"/"},method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView){
        ShiroUser user = (ShiroUser) ShiroKit.getSubject().getPrincipal();
        modelAndView.setViewName("/system/admin/index");
        List<RcPrivilege> privilegeList = user.getPrivilegeList();
        if (null != privilegeList){
            List<String> ids = new ArrayList<>();
            for (RcPrivilege p : privilegeList){
                if (!ids.contains(p.getMenuId())){
                    ids.add(p.getMenuId());
                }
            }
            List<Integer> wantList = new ArrayList<>();
            //得到一级菜单
            wantList.add(1);
            List<RcMenu> menuList = menuService.selectInIds(ids,wantList);
            wantList.clear();
            if (menuList != null){
                //得到二级菜单
                wantList.add(2);
                List<RcMenu> secondMenuList = menuService.selectInIds(ids,wantList);
                for (RcMenu menu:menuList) {
                    List<RcMenu> childList = new ArrayList<>();
                    for (RcMenu nu:secondMenuList
                         ) {
                        if (menu.getId().equals(nu.getpId())){
                            childList.add(nu);
                        }
                    }
                    childList.sort(new MenuComparator());
                    menu.setChild(childList);
                }
                menuList.sort(new MenuComparator());
                modelAndView.addObject("menuList",menuList);
            }
        }
        return modelAndView;
    }
}
