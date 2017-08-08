package com.common.system.mapper;

import com.common.system.entity.RcMenu;
import com.common.system.entity.RcMenuExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RcMenuMapper {
    int deleteByExample(RcMenuExample example);

    int deleteByPrimaryKey(String id);

    int insert(RcMenu record);

    int insertSelective(RcMenu record);

    List<RcMenu> selectByExample(RcMenuExample example);

    RcMenu selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RcMenu record, @Param("example") RcMenuExample example);

    int updateByExample(@Param("record") RcMenu record, @Param("example") RcMenuExample example);

    int updateByPrimaryKeySelective(RcMenu record);

    int updateByPrimaryKey(RcMenu record);

    int updatePcode(@Param("oldPcode") String oldPcode,@Param("newPcode") String newPcode);
}