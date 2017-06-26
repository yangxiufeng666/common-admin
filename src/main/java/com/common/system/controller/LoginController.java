package com.common.system.controller;

import com.common.system.shiro.ShiroKit;
import com.common.system.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
    @RequestMapping(value = {"/postLogin"}, method = RequestMethod.POST)
    public String postLogin(@RequestParam(required = true) String username, @RequestParam(required = true) String password, ModelAndView modelAndView, HttpSession session){
        Subject subject = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password.toCharArray());
        try {
            subject.login(token);
            ShiroUser user = (ShiroUser) subject.getPrincipal();
            modelAndView.addObject("user",user);
            session.setAttribute("user",user);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return REDIRECT + "/";
    }
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(){
        ShiroKit.getSubject().logout();
        return REDIRECT + "/";
    }
}
