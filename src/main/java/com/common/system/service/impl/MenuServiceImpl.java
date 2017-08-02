package com.common.system.service.impl;

import com.common.system.entity.RcMenu;
import com.common.system.mapper.RcMenuMapper;
import com.common.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
