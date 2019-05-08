package com.itpubpil.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangjingbiao
 * @PackageName com.itpubpil.test
 * @Classname IniRealmTest
 * @Date 19-5-8 下午6:34
 * @Description ini文件权限测试
 */
public class IniRealmTest {

    @Test
    public void testAuthentication() {

        // 此类realm主要是从ini文件中获取用户、权限等数据信息
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        // 1.1.构建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 2.构建主体
        Subject subject = SecurityUtils.getSubject();
        // 3.构建认证数据
        UsernamePasswordToken token = new UsernamePasswordToken("wangjingbiao", "123456");

        // 4.主体提交认证
        subject.login(token);

        // 5.检测主体认证请求是否通过
        Assert.assertTrue(subject.isAuthenticated());

        // 检查角色
        subject.checkRole("admin");

        // 检查权限
        subject.checkPermission("user:delete");

        // 6.主体注销登录
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }
}
