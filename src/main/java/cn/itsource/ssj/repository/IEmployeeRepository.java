package cn.itsource.ssj.repository;

import cn.itsource.ssj.domain.Employee;

public interface IEmployeeRepository extends IBaseRepository<Employee,Long> {
    Employee findByUsername(String username);
}
