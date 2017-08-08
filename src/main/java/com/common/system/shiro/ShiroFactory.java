package com.common.system.shiro;

import com.common.system.entity.RcDept;
import com.common.system.entity.RcRelation;
import com.common.system.entity.RcRole;
import com.common.system.entity.RcUser;
import com.common.system.mapper.RcDeptMapper;
import com.common.system.mapper.RcRelationMapper;
import com.common.system.mapper.RcRoleMapper;
import com.common.system.mapper.RcUserMapper;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/20.
 * Time:16:46
 * ProjectName:Common-admin
 */
@Service
public class ShiroFactory {
    @Autowired
    private RcUserMapper userMapper;
    @Autowired
    private RcRoleMapper roleMapper;
    @Autowired
    private RcDeptMapper deptMapper;

    public RcUser user(String username) {
        RcUser user = userMapper.getUserByName(username);
        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        return user;
    }
    public ShiroUser shiroUser(RcUser user) {
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(user.getId());            // 账号id
        shiroUser.setUsername(user.getUsername());// 账号
        shiroUser.setDeptId(user.getDeptid());    // 部门id
        RcDept dept = deptMapper.selectByPrimaryKey(user.getDeptid());
        if (dept != null){
            shiroUser.setDeptName(dept.getSimpleName());
        }
        shiroUser.setName(user.getName());        // 用户名称
        shiroUser.setRoleId(user.getRoleId());
        //角色名称
        RcRole rcRole = roleMapper.selectByPrimaryKey(user.getRoleId());
        if (rcRole != null){
            shiroUser.setRoleName(rcRole.getName());
            shiroUser.setRoleValue(rcRole.getValue());
        }
        shiroUser.setCreateTime(user.getCreateTime());
        return shiroUser;
    }
    public SimpleAuthenticationInfo buildAuthenticationInfo(ShiroUser shiroUser, RcUser user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}
