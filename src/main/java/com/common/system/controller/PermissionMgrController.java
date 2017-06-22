package com.common.system.controller;

import com.common.system.entity.RcPermission;
import com.common.system.service.PermissionService;
import com.common.system.util.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}
