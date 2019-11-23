package cn.itsource.test.repository;

import cn.itsource.domain.Employee;
import cn.itsource.repository.IEmployeeRepository;
import cn.itsource.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class IEmployeeRepositoryTest extends BaseTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void testFindOne() throws Exception{
        System.out.println(employeeRepository);     //SimpleJpaRepository
        System.out.println(employeeRepository.getClass());  //com.sun.proxy.$Proxy27 动态代理
        Employee employee = employeeRepository.findOne(1L);
        System.out.println(employee);
    }
    @Test
    public void testFindAll() throws Exception{
        List<Employee> list = employeeRepository.findAll();
        list.forEach(employee -> System.out.println(employee));
    }
    @Test
    public void testSave() throws Exception{
        Employee employee = new Employee();         //换成SpringMVC接收前段表单数据
        employee.setAge(22);
        //employee.setDepartment_id(1L);
        employee.setEmail("1234@qq.com");
        employee.setHeadImage("/aaa/bbb.png");
        employee.setUsername("admin100");
        employee.setPassword("123");
        employeeRepository.save(employee);
    }
    @Test
    public void testUpdate() throws Exception{
        Employee employee = new Employee();         //换成SpringMVC接收前段表单数据
        employee.setId(274L);
        employee.setAge(32);
        //employee.setDepartment_id(1L);
        employee.setEmail("123456@qq.com");
        employee.setHeadImage("/aaa/bbb.png");
        employee.setUsername("admin100");
        employee.setPassword("123");
        employeeRepository.save(employee);
    }
    @Test
    public void testDelete() throws Exception{
        employeeRepository.delete(274L);
    }

    /**
     * 分页查询
     * @throws Exception
     */
    @Test
    public void testFindByPage() throws Exception{
        /**
         * int page 当前页码 从0开始
         * int size 每页显示多少行数据
         */
        Pageable pageable = new PageRequest(13, 10);
        Page<Employee> page = employeeRepository.findAll(pageable);
        System.out.println(page.getTotalElements());        //总行数
        System.out.println(page.getTotalPages());           //总页数
        System.out.println(page.getNumber());               //当前页码
        System.out.println(page.getNumberOfElements());     //当前页有多少行数据
        System.out.println(page.getSize());                 //每页查询多少行数据
        System.out.println(page.getContent());              //当前页的数据集合
    }

    /**
     * 分页+排序
     * @throws Exception
     */
    @Test
    public void testFindByPageAndSort() throws Exception{
        /**
         * int page 当前页码 从0开始
         * int size 每页显示多少行数据
         * Sort sort 排序
         *  Sort.Direction.ASC表示升序
         *  Sort.Direction.DESC表示降序
         */
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"age");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order,order2);
        Pageable pageable = new PageRequest(0,10, sort);
        Page<Employee> page = employeeRepository.findAll(pageable);
        page.getContent().forEach(employee -> System.out.println(employee));
    }

    /**
     * 简单高级查询
     * @throws Exception
     */
    @Test
    public void testFindAllByUsernameLike() throws Exception{
        List<Employee> list = employeeRepository.findAllByUsernameLike("admin1%");
        list.forEach(employee -> System.out.println(employee));
    }

    /**
     * 自定义JPQL高级查询
     * @throws Exception
     */
    @Test
    public void testFindEmpsWithManyConditions() throws Exception{
        List<Employee> list = employeeRepository.findEmpsWithManyConditions("admin1%");
        list.forEach(employee -> System.out.println(employee));
    }

    /**
     * 原生SQL语句查询
     * @throws Exception
     */
    @Test
    public void testFindBySQL() throws Exception{
        employeeRepository.findBySQL("admin1%").forEach(employee -> System.out.println(employee));
    }

}
