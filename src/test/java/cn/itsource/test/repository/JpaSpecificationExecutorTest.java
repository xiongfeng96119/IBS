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
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class JpaSpecificationExecutorTest extends BaseTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    /**
     * 通过用户名模糊查询
     * @throws Exception
     */
    @Test
    public void testFindByOneCondition() throws Exception{
        //Specification 规范，其实就是查询条件
        List<Employee> list = employeeRepository.findAll(new Specification<Employee>(){
            /**
             * @param root          通过root变量可以获取到你要做的高级查询是哪个字段
             * @param criteriaQuery 多个条件使用and还是or来连接
             * @param criteriaBuilder   连接字段与值之间的运算符 比如< <= > >= = != like等等
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");       //得到domain实体类的一个字段
                return criteriaBuilder.like(username, "admin1%");
            }
        });
        list.forEach(employee -> System.out.println(employee));
    }

    /**
     * 多条件高级查询：通过用户名模糊查询、年龄范围查询、邮箱模糊查询
     * @throws Exception
     */
    @Test
    public void testFindByConditions() throws Exception{
        List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate predicate01 = criteriaBuilder.like(username, "admin1%");
                Path age = root.get("age");
                Predicate predicate02 = criteriaBuilder.between(age, 22, 26);
                Path email = root.get("email");
                Predicate predicate03 = criteriaBuilder.like(email, "%11%");
                //将多个条件合并  默认使用and来连接
                criteriaQuery = criteriaQuery.where(predicate01, predicate02, predicate03);
                return criteriaQuery.getRestriction();
            }
        });
        list.forEach(employee -> System.out.println(employee));
    }

    /**
     * 多条件高级查询+分页+排序
     * @throws Exception
     */
    @Test
    public void testFindByConditionsWithPageable() throws Exception{
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        Pageable pageable = new PageRequest(1,2, sort);
        Page<Employee> page = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate predicate01 = criteriaBuilder.like(username, "admin1%");
                Path age = root.get("age");
                Predicate predicate02 = criteriaBuilder.between(age, 22, 26);
                Path email = root.get("email");
                Predicate predicate03 = criteriaBuilder.like(email, "%11%");
                //将多个条件合并  默认使用and来连接
                criteriaQuery = criteriaQuery.where(predicate01, predicate02, predicate03);
                return criteriaQuery.getRestriction();
            }
        }, pageable);
        page.getContent().forEach(employee -> System.out.println(employee));
    }


}
