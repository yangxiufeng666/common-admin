package com.common.system.service.impl;

import com.common.system.entity.RcRole;
import com.common.system.entity.RcUser;
import com.common.system.entity.RcUserExample;
import com.common.system.mapper.RcUserMapper;
import com.common.system.service.RoleService;
import com.common.system.service.UserService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/26.
 * Time:13:59
 * ProjectName:Common-admin
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RcUserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public Result<Integer> deleteById(Integer userId) {
        Result<Integer> result = new Result<>();
        try {
            userMapper.deleteByPrimaryKey(userId);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result<RcUser> getById(Integer userId) {
        Result<RcUser> result = new Result<>();
        RcUser user = userMapper.selectByPrimaryKey(userId);
        Result<RcRole> role = roleService.selectById(user.getRoleId());
        if (role.isStatus()){
            user.setRole(role.getData());
        }
        if (user != null){
            result.setData(user);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        }
        return result;
    }

    @Override
    public Result<Integer> update(RcUser user) {
        Result<Integer> result = new Result<>();
        try {
            userMapper.updateByPrimaryKeySelective(user);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException){
                result.setMsg("昵称已经存在");
            }
        }
        return result;
    }

    @Override
    public Result<Integer> save(RcUser user) {
        Result<Integer> result = new Result<>();
        try {
            userMapper.insert(user);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException){
                result.setMsg("账号已经存在");
            }
        }
        return result;
    }

    @Override
    public Result<RcUser> getByUserName(String username) {
        Result<RcUser> result = new Result<>();
        RcUser user = userMapper.getUserByName(username);
        if (user != null){
            result.setData(user);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        }
        return result;
    }

    @Override
    public PageInfo<RcUser> listForPage(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<RcUser> roleList = userMapper.selectByExample(new RcUserExample());
        for (RcUser u: roleList
             ) {
            Result<RcRole> result = roleService.selectById(u.getRoleId());
            if (result.isStatus()){
                u.setRole(result.getData());
            }
        }
        return new PageInfo<>(roleList);
    }

    @Override
    public int modifyPwd(RcUser user) {
        user.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
