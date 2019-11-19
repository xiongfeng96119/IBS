package cn.itsource.ssj.service;

import cn.itsource.ssj.domain.Department;

public interface IDepartmentService extends IBaseService<Department>{
    /**
     * 通过部门名称查询部门对象
     * @return
     */
    Department findByName(String name);
}
