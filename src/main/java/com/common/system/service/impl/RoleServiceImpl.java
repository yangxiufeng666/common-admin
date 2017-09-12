package com.common.system.service.impl;

import com.common.system.entity.RcRole;
import com.common.system.entity.RcRoleExample;
import com.common.system.entity.RcRoleWrapper;
import com.common.system.mapper.RcRoleMapper;
import com.common.system.service.RoleService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<RcRole> roleList = roleMapper.getRoleList();
        return new PageInfo<>(roleList);
    }

    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RcRole selectByRoleName(String roleName) {
        RcRoleExample roleExample = new RcRoleExample();
        RcRoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andNameEqualTo(roleName);
        List<RcRole> resultData = roleMapper.selectByExample(roleExample);
        if (resultData.size() < 1) {
            return null;
        }
        return resultData.get(0);
    }
    @Override
    public RcRole selectByRoleValue(String roleValue) {
        RcRoleExample roleExample = new RcRoleExample();
        RcRoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andValueEqualTo(roleValue);
        List<RcRole> resultData = roleMapper.selectByExample(roleExample);
        if (resultData.size() < 1) {
            return null;
        }
        return resultData.get(0);
    }
    @Override
    public Result<Integer> save(RcRole role, List<Integer> permissionIds) {
        Result<Integer> result=new Result<>();
        result.setStatus(false);
        result.setCode(MsgCode.FAILED);
        if (selectByRoleName(role.getName()) != null){
            result.setMsg("角色名已存在");
            return result;
        }
        if (selectByRoleValue(role.getValue()) != null){
            result.setMsg("角色值已存在");
            return result;
        }
        roleMapper.insert(role);
        role = selectByRoleName(role.getName());
        return result;
    }

    @Override
    public Result<RcRole> selectById(Integer id) {
        Result<RcRole> result = new Result<>();
        RcRole role = roleMapper.selectByPrimaryKey(id);
        if (role == null){
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            result.setMsg("没有该角色");
            return result;
        }
        result.setData(role);
        result.setStatus(true);
        result.setCode(MsgCode.SUCCESS);
        return result ;
    }

    @Override
    public Result<Integer> update(RcRole role) {
        Result<Integer> result = new Result<>();
        result.setStatus(false);
        result.setCode(MsgCode.FAILED);
        if (!StringUtils.hasText(role.getName())) {
            result.setMsg("角色名不能为空");
            return result;
        }
        if (!StringUtils.hasText(role.getValue())) {
            result.setMsg("角色值不能为空");
            return result;
        }
        try {
            roleMapper.updateByPrimaryKeySelective(role);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            result.setMsg("操作失败："+e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public List<RcRoleWrapper> getRoleWrapperList() {
        List<RcRole> roleList = roleMapper.getRoleList();
        List<RcRoleWrapper> wraps = new ArrayList<>();
        if (roleList != null){
            for (RcRole role:roleList
                    ) {
                RcRoleWrapper wrapper = new RcRoleWrapper();
                wrapper.setId(role.getId());
                wrapper.setStatus(role.getStatus());
                wrapper.setName(role.getName());
                wrapper.setValue(role.getValue());
                wrapper.setTips(role.getTips());
                wrapper.setCreateTime(role.getCreateTime());
                wraps.add(wrapper);
            }
        }
        return wraps;
    }
}
