package com.itpubpil.test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wangjingbiao
 * @PackageName com.itpubpil.test
 * @Classname MyRealm
 * @Date 19-5-8 下午7:33
 * @Description 实现自定义realm
 */
public class MyRealm extends AuthorizingRealm {
    Map<String, String> userMap = new HashMap<>(16);

    {
        userMap.put("wangjingbiao", "123456");
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        Set<String> roles = getRolesByUserName(userName); 
        Set<String> permissions = getPermissionsByUserName(userName);
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        simpleAuthenticationInfo.setStringPermissions(permissions);
        simpleAuthenticationInfo.setRoles(roles);
        return simpleAuthenticationInfo;
    }

    /**
     * 返回用户的权限信息
     * @param userName
     * @return
     */
    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("user:add");
        sets.add("user:remove");
        return sets;
    }

    /**
     * 返回用户的角色信息
     * @param userName
     * @return
     */
    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token instanceof MyUserNamePasswordToken){
            MyUserNamePasswordToken token1 = (MyUserNamePasswordToken) token;
            // 1.从主体传过来的认证信息中获取用户名
            String userName = (String) token1.getPrincipal();
            String password = (String) token1.getCredentials();
            // 2.从数据库中获取密码，这里暂时用map来进行代替
            String needPassword = getPasswordByUserName(userName);
            if (needPassword.equals(password)){
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,"MyRealm");
                return authenticationInfo;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    /**
     * 模拟数据库查询操作
     *
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }
}
