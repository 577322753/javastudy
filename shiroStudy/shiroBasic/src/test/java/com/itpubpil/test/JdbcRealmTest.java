package com.itpubpil.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wangjingbiao
 * @PackageName com.itpubpil.test
 * @Classname JdbcRealmTest
 * @Date 19-5-8 下午6:43
 * @Description jdbcRealm测试
 */
public class JdbcRealmTest {
    DruidDataSource dataSource;

    @Before
    public void initDatasource(){
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/shiro_test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("dbManager");
    }

    @Test
    public void testAuthentication() {

        // 此类realm主要是从ini文件中获取用户、权限等数据信息
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        // 设置成true才能去查询权限数据
        jdbcRealm.setPermissionsLookupEnabled(true);

        // 可以自定义sql语句去查询权限
        String sql = "select password2 from test_user where username2 = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        // 1.1.构建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 2.构建主体
        Subject subject = SecurityUtils.getSubject();
        // 3.构建认证数据
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "666");

        // 4.主体提交认证
        subject.login(token);

        // 5.检测主体认证请求是否通过
        Assert.assertTrue(subject.isAuthenticated());

        // 检查角色
        subject.checkRole("system");

        // 检查权限
        //subject.checkPermission("update");

        // 6.主体注销登录
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }
}
