package com.common.system.controller;

import com.common.system.entity.RcPermission;
import com.common.system.entity.RcRole;
import com.common.system.entity.ZTreeNode;
import com.common.system.service.PermissionService;
import com.common.system.service.RelationService;
import com.common.system.service.RoleService;
import com.common.system.service.ZTreeService;
import com.common.system.util.Convert;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/21.
 * Time:15:47
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping(value = "role")
public class RoleMgrController extends BaseController{

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private ZTreeService treeService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/role/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<RcRole> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo pageInfo = roleService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<RcRole>(pageInfo);
    }
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public @ResponseBody String delete(@PathVariable Integer id){
        roleService.deleteById(id);
        return REDIRECT+"/system/admin/role/list";
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelMap){
        List<RcPermission> permissionList = getRcPermissions();
        modelMap.addObject("permissions",permissionList);
        modelMap.setViewName("/system/admin/role/add");
        return modelMap;
    }
    @RequestMapping(value = "save")
    public @ResponseBody Result save(String roleName,String roleValue,String permission){
        RcRole rcRole = new RcRole();
        rcRole.setName(roleName);
        rcRole.setValue(roleValue);
        rcRole.setStatus(1);
        rcRole.setCreateTime(new Date());
        List<Integer> permissionList = Convert.toIntegerList(permission,",");
        Result<Integer> result = roleService.save(rcRole,permissionList);
        return result;
    }
    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView modelAndView,@PathVariable Integer id){
        Result<RcRole> result = roleService.selectById(id);
        modelAndView.addObject("role",result.getData());
        List<RcPermission> permissionList = getRcPermissions();
        modelAndView.addObject("permissions",permissionList);
        modelAndView.setViewName("/system/admin/role/edit");
        return modelAndView;
    }
    @RequestMapping(value = "update")
    public @ResponseBody Result update(RcRole role,String permission){
        role = roleService.selectById(role.getId()).getData();
        List<Integer> permissionList = Convert.toIntegerList(permission,",");
        role.setUpdateTime(new Date());
        Result<Integer> result = roleService.update(role);
        if (result.isStatus()){
            result = relationService.update(role.getId(),permissionList);
        }
        return result;
    }
    @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id,ModelAndView modelAndView){
        Result<RcRole> result = roleService.selectById(id);
        modelAndView.addObject("role",result.getData());
        modelAndView.setViewName("/system/admin/role/view");
        return modelAndView;
    }
    @RequestMapping(value = "permission/{id}",method = RequestMethod.GET)
    public ModelAndView dispatchPermission(@PathVariable Integer id,ModelAndView modelAndView){
        List<ZTreeNode> treeNodes = treeService.getZTreeNodes();
        ZTreeNode node=null;
        for (ZTreeNode n:treeNodes) {
            if (n.getpId().equals("0")){
                node = n;
                break;
            }
        }
        treeNodes.remove(node);
        String treeStr = treeService.buildZTree(treeNodes);
        modelAndView.addObject("zNodes",treeStr);
        modelAndView.addObject("roleId",id);
        modelAndView.setViewName("/system/admin/role/privilege");
        return modelAndView;
    }


    private List<RcPermission> getRcPermissions() {
        PageInfo pageInfo = permissionService.listForPage(null,null);
        return pageInfo.getList();
    }
}
