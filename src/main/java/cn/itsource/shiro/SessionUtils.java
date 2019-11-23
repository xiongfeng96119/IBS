package cn.itsource.shiro;

import cn.itsource.domain.Employee;
import org.apache.shiro.SecurityUtils;

public class SessionUtils {

    public static void setUser(Employee employee){
        SecurityUtils.getSubject().getSession().setAttribute("loginUser", employee);
    }

    public static Employee getUser(){
        return (Employee)SecurityUtils.getSubject().getSession().getAttribute("loginUser");
    }

}
