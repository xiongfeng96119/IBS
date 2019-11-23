package cn.itsource.test.repository;

import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.repository.IEmployeeRepository;
import cn.itsource.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public class IBaseRepositoryTest extends BaseTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void test() throws Exception{
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("admin1");
        Page<Employee> page = employeeRepository.findPageByQuery(employeeQuery);
        page.forEach(employee -> System.out.println(employee));
    }


}
