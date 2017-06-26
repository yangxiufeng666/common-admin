package com.common.system.mapper;

import com.common.system.entity.RcRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RcRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RcRelation record);

    int insertSelective(RcRelation record);

    RcRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcRelation record);

    int updateByPrimaryKey(RcRelation record);

    List<RcRelation> getByRoleId(Integer roleId);

    int deleteByRoleId(Integer roleId);

    int deleteByPermissionId(Integer pId);
}