package com.common.system.mapper;

import com.common.system.entity.RcUser;

public interface RcUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RcUser record);

    int insertSelective(RcUser record);

    RcUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcUser record);

    int updateByPrimaryKey(RcUser record);
}