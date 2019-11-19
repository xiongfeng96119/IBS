package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.repository.IEmployeeRepository;
import cn.itsource.ssj.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    /**
     * 新增和修改时验证用户名是否被占用
     * @param username
     * @param id
     * @return true表示被占用  false表示未被占用
     */
    @Override
    public boolean checkUsername(String username, Long id) {
        List<Employee> list = null;
        if(id!=null&&id>0){
            String jpql = "select e from Employee e where e.username=?1 and e.id!=?2";
            list = employeeRepository.findByJpql(jpql, username, id);
        }else {
            String jpql = "select e from Employee e where e.username=?1";
            list = employeeRepository.findByJpql(jpql, username);
        }
        if (list==null||list.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Employee> findAllBuyer() {
        List<Employee> list = employeeRepository.findByJpql("select e from Employee e where e.department.id = 2");
        return list;
    }


}
