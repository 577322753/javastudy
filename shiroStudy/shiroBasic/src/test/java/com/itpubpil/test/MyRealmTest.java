package com.itpubpil.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangjingbiao
 * @PackageName com.itpubpil.test
 * @Classname MyRealmTest
 * @Date 19-5-8 下午7:51
 * @Description 测试自定义的Realm对象
 */
public class MyRealmTest {

    @Test
    public void testAuthentication() {

        MyRealm myRealm = new MyRealm();
        // 1.1.构建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(myRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 2.构建主体
        Subject subject = SecurityUtils.getSubject();
        // 3.构建认证数据
        AuthenticationToken authenticationToken = new MyUserNamePasswordToken("wangjingbiao","123456");
        // 4.主体提交认证
        subject.login(authenticationToken);

        // 5.检测主体认证请求是否通过
        Assert.assertTrue(subject.isAuthenticated());

//        // 检查角色
//        subject.checkRole("system");

        // 检查权限
        //subject.checkPermission("update");

        // 6.主体注销登录
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }
}
