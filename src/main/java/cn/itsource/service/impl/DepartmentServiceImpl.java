package cn.itsource.service.impl;

import cn.itsource.domain.Department;
import cn.itsource.repository.IDepartmentRepository;
import cn.itsource.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public Department findByName(String name) {
        List<Department> list = departmentRepository.findByJpql("select d from Department d where d.name=?1", name);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
