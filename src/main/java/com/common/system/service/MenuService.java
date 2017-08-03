package com.common.system.service;

import com.common.system.entity.RcMenu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/8/2.
 * Time:13:59
 * ProjectName:Common-admin
 */
public interface MenuService {

    int deleteByPrimaryKey(String id);

    int insert(RcMenu record);

    RcMenu selectByPrimaryKey(String id);

    int update(RcMenu record);

    PageInfo<RcMenu> listForPage(Integer pageNum, Integer pageSize);

    List<RcMenu> getMenu();
}
