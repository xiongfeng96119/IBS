package cn.itsource.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void test() throws Exception{
        //加载shiro.ini创建一个Factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //通过Factory得到一个SecurityManager对象【shiro的核心对象】
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager对象放入Shiro的上下文中
        SecurityUtils.setSecurityManager(securityManager);

        //获取当前用户（一个程序、或者一个线程只要运行当前这一段代码，就认为是一个当前用户，不论是否已经登录）
        Subject subject = SecurityUtils.getSubject();

        //检查当前用户的登录状态
        System.out.println(subject.isAuthenticated());

        try {
            //登录
            subject.login(new UsernamePasswordToken("guest","123"));
        } catch (UnknownAccountException e){
            //登录时用户名错误就报UnknownAccountException         未知的账户
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            //登录时密码错误就报IncorrectCredentialsException    不正确的凭证（密码）
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //检查当前用户的登录状态
        System.out.println(subject.isAuthenticated());

        //检查角色和权限之前一定要确保当前用户已登录，如果未登录去检查角色和权限一定返回false
        //检查当前用户是否属于admin角色
        System.out.println("检查当前用户是否属于admin角色:" + subject.hasRole("admin"));
        //检查当前用户是否拥有employee:*的权限
        System.out.println("检查当前用户是否拥有employee:*的权限:" + subject.isPermitted("employee:*"));


        //退出登录
        subject.logout();

        //检查当前用户的登录状态
        System.out.println(subject.isAuthenticated());
    }
}
