package cn.itsource.test.service;

import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.service.IEmployeeService;
import cn.itsource.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IEmployeeServiceTest extends BaseTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void test() throws Exception{
        List<Employee> list = employeeService.findAll(new EmployeeQuery());
        list.forEach(employee -> System.out.println(employee));
    }


}
