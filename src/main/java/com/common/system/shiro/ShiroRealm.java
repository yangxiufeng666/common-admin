package com.common.system.shiro;

import com.common.system.entity.RcPermission;
import com.common.system.entity.RcRelation;
import com.common.system.entity.RcUser;
import com.common.system.service.PermissionService;
import com.common.system.service.RelationService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.AllPermission;
import org.apache.shiro.authz.permission.DomainPermission;
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
    private PermissionService permissionService;
    @Autowired
    private RelationService relationService;

    /***
     * <p>授权</p>
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser user = (ShiroUser)principalCollection.getPrimaryPrincipal();
        Integer roleId = user.getRoleId();
        //得到角色权限关系
        List<RcRelation> relationList = relationService.getByRoleId(roleId);

        List<Integer> permissionIds = new ArrayList<>();

        for (RcRelation r : relationList
             ) {
            permissionIds.add(r.getPermissionid());
        }
        //得到权限列表
        List<RcPermission> permissionList = permissionService.getPermissions(permissionIds);

        List<String> permissionValues = new ArrayList<>();

        for (RcPermission p:permissionList
             ) {
            permissionValues.add(p.getPermissionsValue());
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
