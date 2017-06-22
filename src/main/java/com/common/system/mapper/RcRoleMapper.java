package com.common.system.mapper;

import com.common.system.entity.RcRole;
import com.common.system.entity.RcRoleExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RcRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RcRole record);

    int insertSelective(RcRole record);

    List<RcRole> selectByExample(RcRoleExample example);

    RcRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcRole record);

    int updateByPrimaryKey(RcRole record);

    List<RcRole> getRoleList();
}