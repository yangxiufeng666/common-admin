package com.common.system.mapper;

import com.common.system.entity.RcDept;
import com.common.system.entity.RcDeptExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RcDeptMapper {
    int deleteByExample(RcDeptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RcDept record);

    int insertSelective(RcDept record);

    List<RcDept> selectByExample(RcDeptExample example);

    RcDept selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RcDept record, @Param("example") RcDeptExample example);

    int updateByExample(@Param("record") RcDept record, @Param("example") RcDeptExample example);

    int updateByPrimaryKeySelective(RcDept record);

    int updateByPrimaryKey(RcDept record);
}