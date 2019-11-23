package cn.itsource.service.impl;

import cn.itsource.domain.Employee;
import cn.itsource.repository.IEmployeeRepository;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    /**
     * 新增和修改员工的时候验证用户名是否被占用
     * @param username
     * @param id
     * @return true表示被占用 false未被占用
     */
    @Override
    public boolean checkUsername(String username, Long id) {
        List<Employee> list = null;
        if(id != null && id > 0){
            String jpql = "select e from Employee e where e.username=?1 and e.id!=?2";
            list = employeeRepository.findByJpql(jpql, username, id);
        }else{
            String jpql = "select e from Employee e where e.username=?1";
            list = employeeRepository.findByJpql(jpql, username);
        }
        if(list == null || list.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public Employee findByUsername(String username) {
        String jpql = "select e from Employee e where e.username=?1";
        List<Employee> list = employeeRepository.findByJpql(jpql, username);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
