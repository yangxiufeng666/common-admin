package com.common.system.controller;

import com.common.system.entity.RcPermission;
import com.common.system.entity.RcRole;
import com.common.system.service.PermissionService;
import com.common.system.service.RelationService;
import com.common.system.util.Convert;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by Mr.Yangxiufeng on 2017/6/21.
 * Time:15:48
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping(value = "permission")
public class PermissionMgrController extends BaseController {

    private static final Log LOG = LogFactory.getLog(PermissionMgrController.class);

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RelationService relationService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/admin/permission/list");
        return modelAndView;
    }

    /**
     * 分页查询
     *
     * @param start
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<RcPermission> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo pageInfo = permissionService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<RcPermission>(pageInfo);
    }
    @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id, ModelAndView modelAndView){
        Result<RcPermission> result = permissionService.getById(id);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/permission/view");
        return modelAndView;
    }
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public @ResponseBody String delete(@PathVariable Integer id){
        permissionService.deleteById(id);
        relationService.deleteByPermissionId(id);
        return REDIRECT+"/system/admin/permission/list";
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelMap){
        modelMap.setViewName("/system/admin/permission/add");
        return modelMap;
    }
    @RequestMapping(value = "save")
    public @ResponseBody Result save(String permissionsName,String permissionsValue){
        Result<Integer> result = permissionService.save(permissionsName,permissionsValue);
        return result;
    }
    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView modelMap,@PathVariable Integer id){
        modelMap.setViewName("/system/admin/permission/edit");
        Result<RcPermission> result = permissionService.getById(id);
        if (result.isStatus()){
            modelMap.addObject("bean",result.getData());
        }
        return modelMap;
    }
    @RequestMapping(value = "update")
    public @ResponseBody Result update(RcPermission permission){
        permission.setUpdateTime(new Date());
        Result<Integer> result = permissionService.update(permission);
        return result;
    }
}
