package com.common.system.service.impl;

import com.common.system.entity.RcPermission;
import com.common.system.entity.RcPermissionExample;
import com.common.system.entity.RcRoleExample;
import com.common.system.mapper.RcPermissionMapper;
import com.common.system.service.PermissionService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:11:10
 * ProjectName:Common-admin
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RcPermissionMapper permissionMapper;

    @Override
    public List<RcPermission> getPermissions(List<Integer> idList) {
        RcPermissionExample example = new RcPermissionExample();
        RcPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        return permissionMapper.selectByExample(example);
    }

    @Override
    public PageInfo<RcPermission> listForPage(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<RcPermission> permissionList = permissionMapper.getPermissions();
        return new PageInfo<>(permissionList);
    }

    @Override
    public List<RcPermission> getPermissionsByRoleId(Integer roleId) {
        RcPermissionExample example = new RcPermissionExample();
        RcPermissionExample.Criteria criteria = example.createCriteria();
        return null;
    }

    @Override
    public Result<RcPermission> getById(Integer id) {
        RcPermission permission = permissionMapper.selectByPrimaryKey(id);
        Result<RcPermission> result = new Result<>();
        if (permission != null){
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
            result.setData(permission);
        }
        return result;
    }

    @Override
    public Result<Integer> deleteById(Integer id) {
        Result<Integer> result = new Result<>();
        int flag = permissionMapper.deleteByPrimaryKey(id);
        if (flag < 1){
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            result.setMsg("删除失败");
        }
        return result;
    }

    @Override
    public Result<Integer> save(String name, String value) {
        Result<Integer> result = new Result<>();
        result.setStatus(false);
        result.setCode(MsgCode.FAILED);
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)){
            result.setMsg("添加失败:权限名或权限值不能为空");
            return result;
        }
        RcPermissionExample example = new RcPermissionExample();
        RcPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionsNameEqualTo(name);
        List<RcPermission> permissions = permissionMapper.selectByExample(example);
        if (permissions != null && permissions.size() > 0){
            result.setMsg("添加失败:权限名已经存在");
            return result;
        }
        criteria = example.createCriteria();
        criteria.andPermissionsValueEqualTo(value);
        permissions = permissionMapper.selectByExample(example);
        if (permissions != null && permissions.size() > 0){
            result.setMsg("添加失败:权限值已经存在");
            return result;
        }
        RcPermission permission = new RcPermission();
        permission.setCreateTime(new Date());
        permission.setPermissionsName(name);
        permission.setPermissionsValue(value);
        permission.setCreateTime(new Date());
        permission.setStatusId(1);
        try {
            permissionMapper.insert(permission);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("操作失败:"+e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public Result<Integer> update(RcPermission permission) {
        Result<Integer> result = new Result<>();
        try {
            permissionMapper.updateByPrimaryKeySelective(permission);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException){
                result.setMsg("权限名或值必须唯一");
            }else {
                result.setMsg(e.getLocalizedMessage());
            }
        }
        return result;
    }
}
