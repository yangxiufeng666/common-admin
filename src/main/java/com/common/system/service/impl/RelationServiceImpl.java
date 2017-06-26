package com.common.system.service.impl;

import com.common.system.entity.RcRelation;
import com.common.system.mapper.RcRelationMapper;
import com.common.system.service.RelationService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:11:19
 * ProjectName:Common-admin
 */
@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    private RcRelationMapper relationMapper;
    @Override
    public List<RcRelation> getByRoleId(Integer roleId) {
        return relationMapper.getByRoleId(roleId);
    }

    @Override
    public Result<Integer> save(Integer roleId, List<Integer> permissionIds) {
        Result<Integer> result = new Result<>();
        if (roleId < 1){
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            result.setMsg("roleId为空");
            return result;
        }
        if (permissionIds == null || permissionIds.size() == 0){
            result.setCode(MsgCode.SUCCESS);
            result.setStatus(true);
            result.setMsg("权限列表为空，没有需要插入的数据");
            return result;
        }
        RcRelation relation = new RcRelation();
        for (Integer id:permissionIds) {
            relation.setRoleid(roleId);
            relation.setPermissionid(id);
            relation.setCreateTime(new Date());
            relationMapper.insert(relation);
        }
        result.setCode(MsgCode.SUCCESS);
        result.setStatus(true);
        result.setMsg("操作成功");
        return result;
    }

    @Override
    public Result<Integer> update(Integer roleId, List<Integer> permissionList) {
        Result<Integer> result = new Result<Integer>();
        if (roleId < 1) {
            result.setMsg("此角色id无效");
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            return result;
        }
        relationMapper.deleteByRoleId(roleId);
        return save(roleId,permissionList);
    }

    @Override
    public int deleteByRoleId(Integer roleId) {
        return relationMapper.deleteByRoleId(roleId);
    }

    @Override
    public int deleteByPermissionId(Integer permissionId) {
        return relationMapper.deleteByRoleId(permissionId);
    }
}
