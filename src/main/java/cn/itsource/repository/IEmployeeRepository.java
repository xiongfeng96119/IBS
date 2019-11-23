package cn.itsource.repository;

import cn.itsource.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 使用SpringDataJPA，只需要自定义一个接口继承JpaRepository接口即可，里面什么东西都不用谢，
 * 就可以增删改查、高级查询、分页和排序功能全都有了
 * 两个泛型
 *  T  表示domain实体类类型
 *  ID 表示domain实体类中的主键字段的类型
 */

public interface IEmployeeRepository extends IBaseRepository<Employee,Long> {

    /**
     * SpringDataJPA支持通过方法名称来实现简单高级查询
     * 方法名称必须遵守以下规范：
     *  ① 方法名称必须以findAllBy或者findBy开头
     *  ② findAllBy或者findBy后面紧跟domain实体类中的属性名称
     *  ③ 属性名称后面紧跟查询条件符号
     *      Like            like
     *      LessThanEqual   <=
     *      LessThan        <
     *      GreaterThanEqual >=
     *      GreaterThan     >
     *      Equals          =
     *      in              in
     *      Between         between
     *  ④ 如果是Like，抽象方法的参数必须是String类型，而且传入的时候还要加%或者_
     *  ⑤ 如果是LessThan、LessThanEqual、GreaterThan、GreaterThanEqual，就要求抽象方法的参数类型必须是整数、
     *  浮点数、BigDecimal、Date类型
     *  ⑥ 如果是Between，抽象方法的参数类型必须是整数、浮点数、BigDecimal、Date类型，而且必须是两个参数
     *  ⑦ 如果是Equals，则可以省略不写
     *  ⑧ 多个查询条件可以使用And或者Or来连接
     * 只要你的方法遵守这些规范，SpringDataJPA就会自动帮我们生成此方法的实现
     * 缺点就是：方法名称太长
     * @param username
     * @return
     */
    List<Employee> findAllByUsernameLike(String username);


    /**
     * @Query告诉SpringDataJPA，传入一个jpql语句，让SpringDataJPA帮我们写方法实现
     * @param username
     * @return
     */
    @Query("select e from Employee e where e.username like ?1")
    List<Employee> findEmpsWithManyConditions(String username);

    /**
     * 执行原生SQL语句
     * @param username
     * @return
     */
    @Query(nativeQuery=true,value = "SELECT * FROM employee WHERE username LIKE ?1")
    List<Employee> findBySQL(String username);

}
