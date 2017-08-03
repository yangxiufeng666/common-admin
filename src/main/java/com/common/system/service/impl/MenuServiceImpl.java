package com.common.system.service.impl;

import com.common.system.entity.RcMenu;
import com.common.system.entity.RcMenuExample;
import com.common.system.mapper.RcMenuMapper;
import com.common.system.service.MenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/8/2.
 * Time:14:01
 * ProjectName:Common-admin
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private RcMenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RcMenu record) {
        return menuMapper.insert(record);
    }

    @Override
    public RcMenu selectByPrimaryKey(String id) {
        return selectByPrimaryKey(id);
    }

    @Override
    public int update(RcMenu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<RcMenu> listForPage(Integer pageNum, Integer pageSize) {
        List<RcMenu> list = menuMapper.selectByExample(new RcMenuExample());
        PageInfo<RcMenu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<RcMenu> getMenu() {
        List<RcMenu> list = menuMapper.selectByExample(new RcMenuExample());
        return list;
    }
}
