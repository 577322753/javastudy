package com.itpubpil.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wangjingbiao
 * @PackageName com.itpubpil.test
 * @Classname AuthenticationTest
 * @Date 19-5-8 下午6:13
 * @Description shiro基本登录、授权过程，test001
 */
public class AuthenticationTest {
    // 构建realm，用于处理提交认证请求
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("wangjingbiao","123456","admin","user");
    }

    @Test
    public void testAuthentication() {
        // 1.1.构建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 1.2 为securityManager设置主体请求处理realm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 2.构建主体
        Subject subject = SecurityUtils.getSubject();
        // 3.构建认证数据
        UsernamePasswordToken token = new UsernamePasswordToken("wangjingbiao", "123456");

        // 4.主体提交认证
        subject.login(token);

        // 5.检测主体认证请求是否通过
        Assert.assertTrue(subject.isAuthenticated());

//        // 主体认证之后可以进行角色判断
//        subject.checkRole("admin");

        // 6.主体注销登录
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }
}
