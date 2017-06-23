package com.common.system.service;

import com.common.system.entity.RcPermission;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:11:09
 * ProjectName:Common-admin
 */
public interface PermissionService {
    List<RcPermission> getPermissions(List<Integer> idList);

    PageInfo<RcPermission> listForPage(Integer pageNum, Integer pageSize);
    List<RcPermission> getPermissionsByRoleId(Integer id);

}
