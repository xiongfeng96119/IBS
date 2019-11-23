package cn.itsource.test.repository;

import cn.itsource.domain.Employee;
import cn.itsource.repository.IEmployeeRepository;
import cn.itsource.test.BaseTest;
import com.github.wenhao.jpa.Specifications;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class JpaSpecTest extends BaseTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    /**
     * 测试文浩的扩展包-单条件查询
     * @throws Exception
     */
    @Test
    public void testFindByOneCondition() throws Exception{

        Specification<Employee> specification = Specifications.<Employee>and()
                .like("username", "admin1%")
                .build();

        List<Employee> list = employeeRepository.findAll(specification);
        list.forEach(employee -> System.out.println(employee));
    }
    /**
     * 测试文浩的扩展包-多条件查询
     * @throws Exception
     */
    @Test
    public void testFindByConditions() throws Exception{

        Specification<Employee> specification = Specifications.<Employee>and()
                .like("username", "admin1%")
                //.between("age", new Range(22,26))
                .ge("age", 22)
                .le("age", 26)
                .like("email","%11%")
                .build();

        List<Employee> list = employeeRepository.findAll(specification);
        list.forEach(employee -> System.out.println(employee));
    }
    /**
     * 测试文浩的扩展包-多条件查询+分页+排序
     * @throws Exception
     */
    @Test
    public void testFindByConditionsWithPageable() throws Exception{

        Specification<Employee> specification = Specifications.<Employee>and()
                .like("username", "admin1%")
                //.between("age", new Range(22,26))
                .ge("age", 22)
                .le("age", 26)
                .like("email","%11%")
                .build();
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        Pageable pageable = new PageRequest(0,2, sort);

        Page<Employee> page = employeeRepository.findAll(specification, pageable);
        page.forEach(employee -> System.out.println(employee));
    }


}
