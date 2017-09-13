package com.common.system.controller;

import com.common.system.entity.RcMenu;
import com.common.system.entity.TreeGridNode;
import com.common.system.entity.TreeGridWrapper;
import com.common.system.entity.ZTreeNode;
import com.common.system.service.MenuService;
import com.common.system.service.SequenceService;
import com.common.system.service.TreeGridService;
import com.common.system.service.ZTreeService;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/8/2.
 * Time:15:00
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private ZTreeService treeService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
//        List<ZTreeNode> zTreeNodeList = treeService.getMenuZTreeNodes();
//        String tree = treeService.buildZTree(zTreeNodeList);
//        modelAndView.addObject("ListzTree",tree);
        modelAndView.setViewName("/system/admin/menu/list");
        return modelAndView;
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/menu/add");
        List<ZTreeNode> zTreeNodeList = treeService.getMenuZTreeNodes();
        String treeStr = treeService.buildZTree(zTreeNodeList);
        modelAndView.addObject("rcMenu",treeStr);
        return modelAndView;
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody
    Result save(RcMenu menu){
        Result<Integer> result = new Result<>();
        menu.setId(sequenceService.getSequenceId());
        menu.setCreateTime(new Date());
        menu.setStatus(1);
        if (menu.getSort() == null){
            menu.setSort(1);
        }
        if (StringUtils.isEmpty(menu.getCode())){
            result.setMsg("菜单编号不能为空");
            return result;
        }
        try {
            menuService.insert(menu);
            result.setStatus(true);
            result.setMsg("OK");
            result.setCode(MsgCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("菜单编码必须唯一");
        }

        return result;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<RcMenu> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<RcMenu> pageInfo = menuService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<RcMenu>(pageInfo);
    }
    @Autowired
    private TreeGridService treeGridService;
    @RequestMapping(value = "getTreeGridMenu",method = RequestMethod.GET)
    public @ResponseBody
    TreeGridWrapper getTreeGridMenu(){
        List<TreeGridNode> list = treeGridService.getMenuTreeGridNodes();
        TreeGridWrapper wrapper = new TreeGridWrapper();
        wrapper.setRows(list);
        wrapper.setTotal(list.size());
        return wrapper;
    }

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id, ModelAndView modelAndView){
        RcMenu menu = menuService.selectByPrimaryKey(id);
        modelAndView.addObject("menu",menu);
        List<ZTreeNode> zTreeNodeList = treeService.getMenuZTreeNodes();
        for (ZTreeNode node:zTreeNodeList
             ) {
            if (node.getCode().equals(menu.getpCode())){
                modelAndView.addObject("pName",node.getName());
                node.setChecked(true);
            }
        }
        String tree = treeService.buildZTree(zTreeNodeList);
        modelAndView.addObject("zTree",tree);
        modelAndView.setViewName("/system/admin/menu/edit");
        return modelAndView;
    }
    @RequestMapping(value = "update")
    public @ResponseBody
    Result update(RcMenu menu,String oldCode){
        Result<Integer> result = new Result<>();
        menu.setUpdateTime(new Date());
        if (StringUtils.isEmpty(menu.getCode())){
            result.setMsg("菜单编号不能为空");
            return result;
        }
        RcMenu temp = menuService.selectCode(menu.getCode());
        if (temp != null && !temp.getId().equals(menu.getId())){
            result.setMsg("菜单编号必须唯一");
            return result;
        }
        int flag = menuService.update(menu);
        if (flag > 0){
            menuService.updatePcode(oldCode,menu.getCode());
        }
        result.setStatus(true);
        result.setMsg("OK");
        result.setCode(MsgCode.SUCCESS);
        return result;
    }
    @RequestMapping(value = "delete/{id}")
    public @ResponseBody
    Result delete(@PathVariable String id){
        Result<Integer> result = new Result<>();
        menuService.deleteByPrimaryKey(id);
        result.setStatus(true);
        result.setMsg("OK");
        result.setCode(MsgCode.SUCCESS);
        return result;
    }
}
