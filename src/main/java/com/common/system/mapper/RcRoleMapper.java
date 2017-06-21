package com.common.system.mapper;

import com.common.system.entity.RcRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RcRole record);

    int insertSelective(RcRole record);

    RcRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcRole record);

    int updateByPrimaryKey(RcRole record);
}