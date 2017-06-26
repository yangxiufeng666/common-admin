package com.common.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mr.Yangxiufeng on 2017/6/20.
 * Time:17:42
 * ProjectName:Common-admin
 */
@RestController
public class IndexController {

    @RequestMapping(value = {"/"},method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/index");
//        modelAndView.addObject("ctx","adminlte");
        return modelAndView;
    }
}
