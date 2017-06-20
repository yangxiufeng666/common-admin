package com.common.system.mapper;

import com.common.system.entity.RcUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RcUser record);

    int insertSelective(RcUser record);

    RcUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcUser record);

    int updateByPrimaryKey(RcUser record);

    RcUser getUserByName(String username);
}