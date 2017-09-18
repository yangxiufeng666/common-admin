package com.common.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.common.system.entity.RcUser;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcUserMapper extends BaseMapper<RcUser> {

    RcUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcUser record);

    RcUser getUserByName(String username);
}