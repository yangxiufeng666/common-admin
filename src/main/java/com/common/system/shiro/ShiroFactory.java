package com.common.system.shiro;

import com.common.system.entity.*;
import com.common.system.mapper.RcDeptMapper;
import com.common.system.mapper.RcRoleMapper;
import com.common.system.mapper.RcUserMapper;
import com.common.system.service.MenuService;
import com.common.system.service.PrivilegeService;
import com.common.system.service.RcUserRoleService;
import com.common.system.service.RoleService;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

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
    @Autowired
    private RcUserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private MenuService menuService;

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
        shiroUser.setName(user.getName());        // 用户名称
        List<RcUserRole> userRoleList = userRoleService.getByUserId(user.getId());
        List<RcRole> roleList = new ArrayList<>();
        if (userRoleList != null && userRoleList.size() > 0){
            for (RcUserRole r: userRoleList
                 ) {
                RcRole role = roleMapper.selectByPrimaryKey(r.getRoleId());
                roleList.add(role);
            }
            shiroUser.setRoleList(roleList);
        }
        shiroUser.setCreateTime(user.getCreateTime());

        if (roleList != null && roleList.size() >0 ){
            //权限菜单值
            List<String> permissionValues = new ArrayList<>();
            List<String> roleValues = new ArrayList<>();
            //角色权限菜单
            List<RcPrivilege> privilegeList = new ArrayList<>();
            for (RcRole r:roleList
                    ) {
                roleValues.add(r.getValue());
                List<RcPrivilege> ll = privilegeService.getByRoleId(r.getId());
                privilegeList.addAll(ll);
            }
            shiroUser.setRoleValues(roleValues);

            shiroUser.setPrivilegeList(privilegeList);
            List<String> ids = new ArrayList<>();
            for (RcPrivilege p : privilegeList){
                //去重
                if (!ids.contains(p.getMenuId())){
                    ids.add(p.getMenuId());
                }
            }
            List<RcMenu> menuList = menuService.selectInIds(ids,null);

            for (RcMenu p:menuList
                    ) {
                permissionValues.add(p.getCode());
            }
            shiroUser.setPermissionValues(permissionValues);
        }
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
