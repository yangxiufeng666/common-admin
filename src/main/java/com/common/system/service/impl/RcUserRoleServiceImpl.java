package com.common.system.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.system.entity.RcUserRole;
import com.common.system.mapper.RcUserRoleMapper;
import com.common.system.service.RcUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangxiufeng
 * @since 2017-09-11
 */
@Service
public class RcUserRoleServiceImpl extends ServiceImpl<RcUserRoleMapper, RcUserRole> implements RcUserRoleService {
    @Override
    public boolean deleteByUserId(Integer userId) {
        return delete(new Wrapper<RcUserRole>() {
            @Override
            public String getSqlSegment() {
                return "where user_id="+userId;
            }
        });
    }

    @Override
    public List<RcUserRole> getByUserId(Integer userId) {
        return selectList(new Wrapper<RcUserRole>() {
            @Override
            public String getSqlSegment() {
                return "where user_id="+userId;
            }
        });
    }
}
