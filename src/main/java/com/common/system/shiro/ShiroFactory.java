package com.common.system.shiro;

import com.common.system.entity.RcUser;
import com.common.system.mapper.RcUserMapper;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        shiroUser.setName(user.getName());        // 用户名称
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
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
