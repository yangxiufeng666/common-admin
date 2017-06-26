package com.common.system.mapper;

import com.common.system.entity.RcUser;
import com.common.system.entity.RcUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RcUserMapper {
    int deleteByExample(RcUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RcUser record);

    int insertSelective(RcUser record);

    List<RcUser> selectByExample(RcUserExample example);

    RcUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RcUser record, @Param("example") RcUserExample example);

    int updateByExample(@Param("record") RcUser record, @Param("example") RcUserExample example);

    int updateByPrimaryKeySelective(RcUser record);

    int updateByPrimaryKey(RcUser record);

    RcUser getUserByName(String username);
}