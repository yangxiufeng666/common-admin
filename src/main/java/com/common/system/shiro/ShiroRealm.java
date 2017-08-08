package com.common.system.shiro;

import com.common.system.entity.*;
import com.common.system.service.MenuService;
import com.common.system.service.PrivilegeService;
import com.common.system.service.RelationService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/20.
 * Time:15:48
 * ProjectName:Common-admin
 */
public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    private ShiroFactory shiroFactory;
    @Autowired
    private RelationService relationService;
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private MenuService menuService;

    /***
     * <p>授权</p>
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser user = (ShiroUser)principalCollection.getPrimaryPrincipal();
        Integer roleId = user.getRoleId();

        List<String> permissionValues = new ArrayList<>();

        //权限2.0版本
        List<RcPrivilege> privilegeList = privilegeService.getByRoleId(roleId);
        List<String> ids = new ArrayList<>();
        for (RcPrivilege p : privilegeList){
            ids.add(p.getMenuId());
        }
        List<RcMenu> menuList = menuService.selectInIds(ids,null);

        for (RcMenu p:menuList
             ) {
            permissionValues.add(p.getCode());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(user.getRoleValue());
        info.addStringPermissions(permissionValues);
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
