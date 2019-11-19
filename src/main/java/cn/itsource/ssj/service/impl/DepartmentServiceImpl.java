package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Department;
import cn.itsource.ssj.repository.IDepartmentRepository;
import cn.itsource.ssj.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService{
    @Autowired
    private IDepartmentRepository departmentRepository;
    /**
     * 通过部门名称查询部门对象
     * @param name
     * @return
     */
    @Override
    public Department findByName(String name) {
        List<Department> list = departmentRepository.findByJpql("select d from Department d where d.name=?1", name);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
