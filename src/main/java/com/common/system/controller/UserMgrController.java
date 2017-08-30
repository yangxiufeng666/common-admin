package com.common.system.controller;

import com.common.system.entity.RcRole;
import com.common.system.entity.RcUser;
import com.common.system.service.RoleService;
import com.common.system.service.UserService;
import com.common.system.shiro.ShiroKit;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/21.
 * Time:15:46
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping(value = "user")
public class UserMgrController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMgrController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/user/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<RcUser> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<RcUser> pageInfo = userService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<RcUser>(pageInfo);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Result delete(@PathVariable Integer id){
        Result<Integer> result = userService.deleteById(id);
        return result;
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/user/add");
        PageInfo<RcRole> pageInfo = roleService.listForPage(null,null);
        List<RcRole> roleList = pageInfo.getList();
        modelAndView.addObject("roles",roleList);
        return modelAndView;
    }
    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
        Result<RcUser> result = userService.getById(id);
        modelAndView.addObject("bean",result.getData());
        PageInfo<RcRole> pageInfo = roleService.listForPage(null,null);
        modelAndView.addObject("roles",pageInfo.getList());
        modelAndView.setViewName("/system/admin/user/edit");
        return modelAndView;
    }
    @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id,ModelAndView modelAndView){
        Result<RcUser> result = userService.getById(id);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/user/view");
        return modelAndView;
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody Result update(Integer id,String name,Integer sex,Integer roleId){
        Result<RcUser> userResult = userService.getById(id);
        Result<Integer> result = new Result<>();
        if (userResult.isStatus()){
            RcUser user = userResult.getData();
            user.setName(name);
            user.setSex(sex);
            user.setRoleId(roleId);
            result = userService.update(user);
        }
        return result;
    }
    @RequestMapping(value = "save")
    public @ResponseBody Result save(RcUser rcUser, @RequestParam(value = "role", required = false) Integer roleId){
        rcUser.setCreateTime(new Date());
        rcUser.setStatus(1);
        String salt = ShiroKit.getRandomSalt(5);
        rcUser.setSalt(salt);
        String saltPwd = ShiroKit.md5(rcUser.getPassword(),salt);
        rcUser.setPassword(saltPwd);
        rcUser.setRoleId(roleId);
        Result<Integer> result = userService.save(rcUser);
        return result;
    }

    /**
     * <p>修改密码</p>
     * @param id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping(value = "modifyPwd",method = RequestMethod.POST)
    public @ResponseBody Result update(Integer id,String oldPwd,String newPwd){
        Result result = new Result();
        if (StringUtils.isEmpty(newPwd)){
            result.setMsg("新密码不能为空");
            return result;
        }
        Result<RcUser> rcUserResult = userService.getById(id);
        RcUser user = rcUserResult.getData();
        String md5pwd = ShiroKit.md5(oldPwd,user.getSalt());
        if (!user.getPassword().equals(md5pwd)){
            result.setCode(MsgCode.FAILED);
            result.setStatus(false);
            result.setMsg("密码不正确");
            return result;
        }
        String salt = ShiroKit.getRandomSalt(5);
        String saltPwd = ShiroKit.md5(newPwd,salt);
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
}
