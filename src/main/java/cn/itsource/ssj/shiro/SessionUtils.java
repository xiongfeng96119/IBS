package cn.itsource.ssj.shiro;

import cn.itsource.ssj.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SessionUtils {
    /**
     * 将当前用户放入session中
     * @param employee
     */
    public static void setUser(Employee employee){
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("loginUser",employee);
    }

    /**
     * 从session中获取用户
     * @return
     */
    public static Employee getUser(){
        Subject subject = SecurityUtils.getSubject();
        Employee User =(Employee) subject.getSession().getAttribute("loginUser");
        return User;
    }
}
