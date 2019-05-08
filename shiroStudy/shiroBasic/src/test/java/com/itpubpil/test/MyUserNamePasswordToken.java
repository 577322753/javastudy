package com.itpubpil.test;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author wangjingbiao
 * @PackageName com.itpubpil.test
 * @Classname MyUserNamePasswordToken
 * @Date 19-5-8 下午7:56
 * @Description 我的认证主体
 */
public class MyUserNamePasswordToken extends UsernamePasswordToken {
    private String userName2;
    private String password2;

    public MyUserNamePasswordToken(String userName2, String password2) {
        this.userName2 = userName2;
        this.password2 = password2;
    }

    @Override
    public Object getPrincipal() {
        return this.userName2;
    }

    @Override
    public Object getCredentials() {
        return this.password2;
    }
}
