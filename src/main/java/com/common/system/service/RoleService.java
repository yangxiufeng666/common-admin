package com.common.system.service;

import com.common.system.entity.RcRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:14:15
 * ProjectName:Common-admin
 */
public interface RoleService {

    PageInfo<RcRole> listForPage(Integer pageNum, Integer pageSize);

}
