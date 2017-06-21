package com.common.system.mapper;

import com.common.system.entity.RcDept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RcDept record);

    int insertSelective(RcDept record);

    RcDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcDept record);

    int updateByPrimaryKey(RcDept record);
}