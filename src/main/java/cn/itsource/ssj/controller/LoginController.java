package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.service.IEmployeeService;
import cn.itsource.ssj.shiro.SessionUtils;
import cn.itsource.ssj.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/{page}")
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;
    //页面跳转
    @RequestMapping(value="/{page}",method = RequestMethod.GET)
    public String index(@PathVariable("page")String page) {
        return page;
    }
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(String username, String password, HttpSession session) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Employee employee = employeeService.findByUsername(username);
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            //未知账户：登录时未通过登录的用户信息查询到对象
            e.printStackTrace();
            /*session.setAttribute("errorMsg","用户名错误");
            return "redirect:/page/login";*/
            return new Result(500, "用户名错误");
        }catch (IncorrectCredentialsException e){
            //密码错误：登录时前台传过来的密码与查询到用户对象的密码不符
            e.printStackTrace();
            /*session.setAttribute("errorMsg","密码错误");
            return "redirect:/page/login";*/
            return new Result(500, "密码错误");
        }catch (AuthenticationException e){
            e.printStackTrace();
            /*session.setAttribute("errorMsg","系统繁忙");
            return "redirect:/page/login";*/
            return new Result(500, "系统繁忙");
        }
        Employee loginUser =(Employee) subject.getPrincipal();
        SessionUtils.setUser(loginUser);
        return new Result(200, "");
        //return "redirect:/page/index";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/page/login";
    }
}
