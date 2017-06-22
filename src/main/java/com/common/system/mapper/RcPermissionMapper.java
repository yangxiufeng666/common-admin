package com.common.system.mapper;

import com.common.system.entity.RcPermission;
import com.common.system.entity.RcPermissionExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RcPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RcPermission record);

    int insertSelective(RcPermission record);

    List<RcPermission> selectByExample(RcPermissionExample example);

    RcPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcPermission record);

    int updateByPrimaryKey(RcPermission record);

    List<RcPermission> getPermissions();
}