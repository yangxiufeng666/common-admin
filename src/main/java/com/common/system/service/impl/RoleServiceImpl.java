package com.common.system.service.impl;

import com.common.system.entity.RcRole;
import com.common.system.mapper.RcRoleMapper;
import com.common.system.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:14:17
 * ProjectName:Common-admin
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RcRoleMapper roleMapper;

    @Override
    public PageInfo<RcRole> listForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<RcRole> roleList = roleMapper.getRoleList();
        return new PageInfo<>(roleList);
    }
}
