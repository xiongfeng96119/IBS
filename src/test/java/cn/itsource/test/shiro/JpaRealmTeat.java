package cn.itsource.test.shiro;

import cn.itsource.shiro.JpaRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JpaRealmTeat {

    @Test
    public void testLogin() throws Exception{
        //创建Shiro的核心对象securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //创建自定义的JpaRealm对象
        JpaRealm jpaRealm = new JpaRealm();

        //设置加密规则（是将登录表单提交过来的密码进行加密，加密之后再与数据库中查询出来的密码进行比对）
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");        //加密方式
        matcher.setHashIterations(10);              //加密次数
        jpaRealm.setCredentialsMatcher(matcher);


        //将自定义的JpaRealm对象设置成securityManager对象的属性
        securityManager.setRealm(jpaRealm);

        //将securityManager对象放入上下文中
        SecurityUtils.setSecurityManager(securityManager);
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        System.out.println(subject.isAuthenticated());

        try {
            //登录
            subject.login(new UsernamePasswordToken("admin","123"));
        } catch (UnknownAccountException e){
            //登录时用户名错误就报UnknownAccountException         未知的账户
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            //登录时密码错误就报IncorrectCredentialsException    不正确的凭证（密码）
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        System.out.println(subject.isAuthenticated());

        System.out.println(subject.isPermitted("employee:*"));

    }

}
