package com.common.system.controller;

import com.common.system.shiro.ShiroKit;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mr.Yangxiufeng on 2017/6/15.
 * Time:15:58
 * ProjectName:Common-admin
 */
@Controller
public class LoginController extends BaseController{
    /**
     * 进入登录页面
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView getLogin(ModelAndView modelAndView){
        modelAndView.setViewName("/system/login");
        return modelAndView;
    }
    /**
     * 进入登录页面
     */
    @RequestMapping(value = {"/postLogin"}, method = RequestMethod.POST)
    public String postLogin(ModelAndView modelAndView,@RequestParam(required = true) String username,@RequestParam(required = true) String password){
        Subject subject = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password.toCharArray());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return REDIRECT + "/";
    }
}
