package com.common.system.service;

import com.common.system.entity.RcRelation;
import com.common.system.util.Result;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:11:19
 * ProjectName:Common-admin
 */
public interface RelationService {

    List<RcRelation> getByRoleId(Integer roleId);

    Result<Integer> save(Integer roleId, List<Integer> permissionIds);

    Result<Integer> update(Integer roleId, List<Integer> permissionList);

    int deleteByRoleId(Integer roleId);
    int deleteByPermissionId(Integer permissionId);
}
