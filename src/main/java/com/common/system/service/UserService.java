package com.common.system.service;

import com.common.system.entity.RcUser;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

/**
 * Created by Mr.Yangxiufeng on 2017/6/26.
 * Time:13:57
 * ProjectName:Common-admin
 */
public interface UserService {
    Result<Integer> deleteById(Integer userId);
    Result<RcUser> getById(Integer userId);
    Result<Integer> update(RcUser user);
    Result<Integer> save(RcUser user);
    Result<RcUser> getByUserName(String username);
    PageInfo<RcUser> listForPage(Integer pageNum, Integer pageSize);

    int modifyPwd(RcUser user);

}
