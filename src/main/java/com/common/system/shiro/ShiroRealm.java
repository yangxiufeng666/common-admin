package com.common.system.shiro;

import com.common.system.entity.*;
import com.common.system.service.MenuService;
import com.common.system.service.PrivilegeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Mr.Yangxiufeng on 2017/6/20.
 * Time:15:48
 * ProjectName:Common-admin
 */
public class ShiroRealm extends AuthorizingRealm{
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private ShiroFactory shiroFactory;

    /***
     * <p>授权</p>
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOGGER.info("doGetAuthorizationInfo");
        ShiroUser user = (ShiroUser)principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(user.getRoleValues());
        info.addStringPermissions(user.getPermissionValues());
        return info;
    }

    /**
     * <p>登录</p>
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        RcUser user = shiroFactory.user(token.getUsername());
        ShiroUser shiroUser = shiroFactory.shiroUser(user);
        SimpleAuthenticationInfo info = shiroFactory.buildAuthenticationInfo(shiroUser,user,super.getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        hashedCredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }
}
