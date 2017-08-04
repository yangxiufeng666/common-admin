package com.common.system.controller;

import com.common.system.entity.RcMenu;
import com.common.system.entity.RcUser;
import com.common.system.entity.ZTreeNode;
import com.common.system.service.MenuService;
import com.common.system.service.SequenceService;
import com.common.system.shiro.ShiroKit;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/8/2.
 * Time:15:00
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private SequenceService sequenceService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/menu/list");
        return modelAndView;
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/menu/add");
        List<RcMenu> list = menuService.getMenu();
        List<ZTreeNode> zTreeNodeList = new ArrayList();
        for (RcMenu menu:list
             ) {
            ZTreeNode node = new ZTreeNode();
            node.setId(menu.getId());
            node.setName(menu.getName());
            node.setpId(menu.getpId());
            node.setCode(menu.getCode());
            node.setLevel(menu.getLevel());
            zTreeNodeList.add(node);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(zTreeNodeList);
            System.out.println(jsonStr);
            modelAndView.addObject("rcMenu",jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @RequestMapping(value = "save")
    public @ResponseBody
    Result save(RcMenu menu){
        Result<Integer> result = new Result<>();
        menu.setId(sequenceService.getSequenceId());
        menu.setCreateTime(new Date());
        menuService.insert(menu);
        result.setStatus(true);
        result.setMsg("OK");
        result.setCode(MsgCode.SUCCESS);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<RcMenu> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<RcMenu> pageInfo = menuService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<RcMenu>(pageInfo);
    }
}
