package com.common.system.controller;

import com.common.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mr.Yangxiufeng on 2017/6/15.
 * Time:15:58
 * ProjectName:Common-admin
 */
@RestController
public class LoginController {
    @Autowired
    UserMapper userMapper;
    /**
     * 进入登录页面
     */
    @RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
    public ModelAndView getLogin(ModelAndView modelAndView){
        modelAndView.setViewName("/system/login");
        return modelAndView;
    }
    /**
     * 进入登录页面
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ModelAndView postLogin(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/index");
        modelAndView.addObject("ctx","adminlte");
        userMapper.selectByPrimaryKey(1);
        return modelAndView;
    }
}
