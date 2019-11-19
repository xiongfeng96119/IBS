package cn.itsource.ssj.service;

import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.domain.Permission;

import java.util.List;
import java.util.Set;

public interface IEmployeeService extends IBaseService<Employee> {
    /**
     * 新增和修改时验证用户名是否被占用
     * @param username
     * @param id
     * @return true表示被占用  false表示未被占用
     */
    boolean checkUsername(String username,Long id);

    Employee findByUsername(String username);

    void save(Employee employee);

    List<Employee> findAllBuyer();
}
