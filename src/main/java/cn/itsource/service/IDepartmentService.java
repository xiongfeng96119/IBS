package cn.itsource.service;

import cn.itsource.domain.Department;

public interface IDepartmentService extends IBaseService<Department> {

    Department findByName(String name);

}
