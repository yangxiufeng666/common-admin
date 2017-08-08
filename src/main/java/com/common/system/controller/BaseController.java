package com.common.system.controller;

import com.common.system.entity.RcUser;
import com.common.system.shiro.ShiroKit;
import com.common.system.shiro.ShiroUser;

/**
 * Created by Mr.Yangxiufeng on 2017/6/20.
 * Time:16:14
 * ProjectName:Common-admin
 */
public class BaseController {
    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected ShiroUser getUser(){
        return (ShiroUser)ShiroKit.getSubject().getPrincipal();
    }
}
