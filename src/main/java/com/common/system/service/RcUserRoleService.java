package com.common.system.service;

import com.common.system.entity.RcUserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangxiufeng
 * @since 2017-09-11
 */
public interface RcUserRoleService extends IService<RcUserRole> {
	boolean deleteByUserId(Integer userId);
}
