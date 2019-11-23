package cn.itsource.test.shiro;

import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.service.IEmployeeService;
import cn.itsource.test.BaseTest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PasswordTest extends BaseTest {

    @Autowired
    private IEmployeeService employeeService;


    /**
     * 重置密码
     * @throws Exception
     */
    @Test
    public void test() throws Exception{
        List<Employee> list = employeeService.findAll(new EmployeeQuery());
        list.forEach(employee -> {
            SimpleHash simpleHash = new SimpleHash("MD5",employee.getUsername(),"itsource.cn",10);
            employee.setPassword(simpleHash.toString());
            employeeService.save(employee);
        });
    }


}
