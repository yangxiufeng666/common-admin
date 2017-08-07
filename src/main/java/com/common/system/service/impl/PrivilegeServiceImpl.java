package com.common.system.service.impl;

import com.common.system.entity.RcPrivilege;
import com.common.system.entity.RcPrivilegeExample;
import com.common.system.mapper.RcPrivilegeMapper;
import com.common.system.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/8/7.
 * Time:11:51
 * ProjectName:Common-admin
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    private RcPrivilegeMapper privilegeMapper;

    @Override
    public int add(RcPrivilege privilege) {
        return privilegeMapper.insert(privilege);
    }

    @Override
    public int delete(RcPrivilege privilege) {
        RcPrivilegeExample example = new RcPrivilegeExample();
        RcPrivilegeExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(privilege.getRoleId());
        criteria.andMenuIdEqualTo(privilege.getMenuId());
        return privilegeMapper.deleteByExample(example);
    }

    @Override
    public List<RcPrivilege> getByRoleId(Integer roleId) {
        RcPrivilegeExample example = new RcPrivilegeExample();
        RcPrivilegeExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return privilegeMapper.selectByExample(example);
    }

    @Override
    public int deleteByRoleId(Integer roleId) {
        RcPrivilegeExample example = new RcPrivilegeExample();
        RcPrivilegeExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return privilegeMapper.deleteByExample(example);
    }
}
