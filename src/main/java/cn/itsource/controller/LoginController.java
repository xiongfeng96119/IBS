package cn.itsource.controller;

import cn.itsource.domain.Employee;
import cn.itsource.shiro.SessionUtils;
import cn.itsource.utils.ResultJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page")
public class LoginController {

    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String index(@PathVariable("page")String page){
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultJson login(String username, String password, HttpSession session){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录
            subject.login(new UsernamePasswordToken(username,password));
        } catch (UnknownAccountException e){
            //登录时用户名错误就报UnknownAccountException         未知的账户
            e.printStackTrace();
            //session.setAttribute("errorMsg", "用户名错误！");
            return new ResultJson(500,"用户名错误！");
        } catch (IncorrectCredentialsException e){
            //登录时密码错误就报IncorrectCredentialsException    不正确的凭证（密码）
            e.printStackTrace();
            //session.setAttribute("errorMsg", "密码错误！");
            return new ResultJson(500,"密码错误！");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //session.setAttribute("errorMsg", "系统繁忙！");
            return new ResultJson(500,"系统繁忙！");
        }
        /**
         * 将当前登录用户对象（主体对象）保存到Shiro的Session中
         * Shiro的Session是在JavaWeb的HttpSession基础上进行了封装和扩展，不但支持JavaWeb、还支持其他语言开发的web项目
         * 也支持CS架构的项目，甚至APP项目也支持，Shiro的Session功能更强大。
         */
        SessionUtils.setUser((Employee) subject.getPrincipal());
        return new ResultJson(200,"");
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //session.invalidate();
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/page/login";
    }

}
