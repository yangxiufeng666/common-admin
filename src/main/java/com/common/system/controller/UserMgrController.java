package com.common.system.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.system.entity.RcRole;
import com.common.system.entity.RcRoleWrapper;
import com.common.system.entity.RcUser;
import com.common.system.entity.RcUserRole;
import com.common.system.service.RcUserRoleService;
import com.common.system.service.RoleService;
import com.common.system.service.UserService;
import com.common.system.shiro.ShiroKit;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.xiaoleilu.hutool.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Yangxiufeng on 2017/6/21.
 * Time:15:46
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping(value = "user")
public class UserMgrController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMgrController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RcUserRoleService userRoleService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/admin/user/list");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<RcUser> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start,
                                         @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "date", required = false) String date,
                                         @RequestParam(value = "search", required = false) String search,
                                         HttpServletRequest request) {
        Map<String,String[]> params = request.getParameterMap();
        PageInfo<RcUser> pageInfo = userService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<RcUser>(pageInfo);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Result delete(@PathVariable Integer id) {
        Result<Integer> result = userService.deleteById(id);
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/admin/user/add");
        return modelAndView;
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id, ModelAndView modelAndView) {
        Result<RcUser> result = userService.getById(id);
        modelAndView.addObject("bean", result.getData());
        modelAndView.setViewName("/system/admin/user/edit");
        return modelAndView;
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id, ModelAndView modelAndView) {
        Result<RcUser> result = userService.getById(id);
        modelAndView.addObject("bean", result.getData());
        modelAndView.setViewName("/system/admin/user/view");
        return modelAndView;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public
    @ResponseBody
    Result update(Integer id, String name, Integer sex) {
        Result<RcUser> userResult = userService.getById(id);
        Result<Integer> result = new Result<>();
        if (userResult.isStatus()) {
            RcUser user = userResult.getData();
            user.setName(name);
            user.setSex(sex);
            result = userService.update(user);
        }
        return result;
    }

    @RequestMapping(value = "save")
    public
    @ResponseBody
    Result save(RcUser rcUser) {
        rcUser.setCreateTime(new Date());
        rcUser.setStatus(1);
        String salt = ShiroKit.getRandomSalt(5);
        rcUser.setSalt(salt);
        String saltPwd = ShiroKit.md5(rcUser.getPassword(), salt);
        rcUser.setPassword(saltPwd);
        Result<Integer> result = userService.save(rcUser);
        return result;
    }

    @RequestMapping(value = "goResetPwd/{id}", method = RequestMethod.GET)
    public ModelAndView goResetPwd(ModelAndView modelAndView, @PathVariable Integer id) {
        modelAndView.setViewName("system/admin/user/reset_pwd");
        RcUser user = userService.getById(id).getData();
        modelAndView.addObject("bean", user);
        return modelAndView;
    }

    @RequestMapping(value = "doResetPwd", method = RequestMethod.POST)
    public
    @ResponseBody
    Result doResetPwd(Integer id, String password) {
        Result result = new Result();
        Result<RcUser> rcUserResult = userService.getById(id);
        RcUser user = rcUserResult.getData();
        String salt = ShiroKit.getRandomSalt(5);
        String saltPwd = ShiroKit.md5(password, salt);
        user.setPassword(saltPwd);
        user.setSalt(salt);
        userService.modifyPwd(user);
        result.setStatus(true);
        result.setCode(MsgCode.SUCCESS);
        result.setMsg("操作成功");
        return result;
    }

    @RequestMapping(value = "goModifyPwd/{id}", method = RequestMethod.GET)
    public ModelAndView goModifyPwd(ModelAndView modelAndView, @PathVariable Integer id) {
        modelAndView.setViewName("system/admin/user/modify_pwd");
        RcUser user = userService.getById(id).getData();
        modelAndView.addObject("bean", user);
        return modelAndView;
    }

    /**
     * <p>修改密码</p>
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
    public
    @ResponseBody
    Result modifyPwd(Integer id, String oldPassword, String password) {
        Result result = new Result();
        if (StringUtils.isEmpty(password)) {
            result.setMsg("新密码不能为空");
            return result;
        }
        Result<RcUser> rcUserResult = userService.getById(id);
        RcUser user = rcUserResult.getData();
        String md5pwd = ShiroKit.md5(oldPassword, user.getSalt());
        if (!user.getPassword().equals(md5pwd)) {
            result.setCode(MsgCode.FAILED);
            result.setStatus(false);
            result.setMsg("密码不正确");
            return result;
        }
        String salt = ShiroKit.getRandomSalt(5);
        String saltPwd = ShiroKit.md5(password, salt);
        user.setPassword(saltPwd);
        user.setSalt(salt);
        try {
            userService.modifyPwd(user);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "goDispatcherRole/{id}", method = RequestMethod.GET)
    public ModelAndView goDispatcherRole(ModelAndView modelAndView, @PathVariable Integer id) {
        modelAndView.setViewName("/system/admin/user/dispatcher_role");
        List<RcRoleWrapper> roleWrappers = roleService.getRoleWrapperList();
        modelAndView.addObject("roles", roleWrappers);
        modelAndView.addObject("userId", id);
        RcUser user = userService.getById(id).getData();
        modelAndView.addObject("bean", user);
        List<RcUserRole> list = userRoleService.selectList(new Wrapper<RcUserRole>() {
            @Override
            public String getSqlSegment() {
                return "where user_id=" + id;
            }
        });
        for (RcRoleWrapper w : roleWrappers) {
            if (list != null) {
                for (RcUserRole r : list) {
                    if (w.getId().equals(r.getRoleId())) {
                        w.setChecked(true);
                    }
                }
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "doDispatcherRole", method = RequestMethod.POST)
    public
    @ResponseBody
    Result doDispatcherRole(Integer id, String roleId) {
        LOGGER.info("roleId=" + roleId);
        Result result = new Result();
        if (StringUtils.isEmpty(roleId)) {
            if (userRoleService.deleteByUserId(id)) {
                result.setStatus(true);
                result.setCode(MsgCode.SUCCESS);
            }
        } else {
            String[] roleIds = roleId.split(",");
            //删除旧记录
            userRoleService.deleteByUserId(id);
            List<RcUserRole> list = new ArrayList<>();
            for (int i = 0; i < roleIds.length; i++) {
                RcUserRole userRole = new RcUserRole();
                userRole.setUserId(id);
                userRole.setRoleId(Integer.valueOf(roleIds[i]));
                userRole.setCreateTime(new Date());
                userRole.setCreateBy(getUser().getName());
                list.add(userRole);
            }
            if (userRoleService.insertBatch(list)) {
                result.setStatus(true);
                result.setCode(MsgCode.SUCCESS);
            }
        }
        return result;
    }

    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void exportExcel(ModelMap modelMap, HttpServletRequest request,
                            HttpServletResponse response) {
        PageInfo<RcUser> result = userService.listForPage(null, null);
        ExportParams params = new ExportParams("用户信息", null, ExcelType.XSSF);
        modelMap.put(NormalExcelConstants.DATA_LIST, result.getList());
        modelMap.put(NormalExcelConstants.CLASS, RcUser.class);
        modelMap.put(NormalExcelConstants.PARAMS, params);
        String fileName = DateUtil.format(new Date(), DateUtil.NORM_DATETIME_PATTERN);
        modelMap.put(NormalExcelConstants.FILE_NAME, "用户信息表:" + fileName);
        PoiBaseView.render(modelMap, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
