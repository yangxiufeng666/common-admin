package com.common.system.service.impl;

import com.common.system.entity.RcRelation;
import com.common.system.mapper.RcRelationMapper;
import com.common.system.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/22.
 * Time:11:19
 * ProjectName:Common-admin
 */
@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    private RcRelationMapper relationMapper;
    @Override
    public List<RcRelation> getByRoleId(Integer roleId) {
        return relationMapper.getByRoleId(roleId);
    }
}
