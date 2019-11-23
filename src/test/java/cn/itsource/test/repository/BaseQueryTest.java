package cn.itsource.test.repository;

import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.repository.IEmployeeRepository;
import cn.itsource.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public class BaseQueryTest extends BaseTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void test() throws Exception{
        //模拟前端表单提交过来的高级查询条件值
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("admin11");
        List<Employee> list = employeeRepository.findAll(employeeQuery.getSpecification());
        list.forEach(employee -> System.out.println(employee));
    }

    @Test
    public void test02() throws Exception{
        //模拟前端表单提交过来的高级查询条件值
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("admin1");
        Page<Employee> page = employeeRepository
                .findAll(employeeQuery.getSpecification(), employeeQuery.getPageable());
        page.forEach(employee -> System.out.println(employee));
    }

}
