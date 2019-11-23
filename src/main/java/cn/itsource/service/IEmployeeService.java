package cn.itsource.service;

import cn.itsource.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee>{

    /**
     * 新增和修改员工的时候验证用户名是否被占用
     * @param username
     * @param id
     * @return true表示被占用 false未被占用
     */
    boolean checkUsername(String username, Long id);

    /**
     * 通过用户名查询员工对象（用来做登录）
     * @param username
     * @return
     */
    Employee findByUsername(String username);

}
