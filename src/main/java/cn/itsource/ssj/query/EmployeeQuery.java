package cn.itsource.ssj.query;

import cn.itsource.ssj.domain.Employee;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeQuery extends BaseQuery<Employee> {
    private String username;
    private Integer age1;
    private Integer age2;
    private Long departmentId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge1() {
        return age1;
    }

    public void setAge1(Integer age1) {
        this.age1 = age1;
    }

    public Integer getAge2() {
        return age2;
    }

    public void setAge2(Integer age2) {
        this.age2 = age2;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public Specification createSpecification() {
        return  Specifications.<Employee>and()
                .like(StringUtils.isNotBlank(username), "username","%"+username+"%")
                .gt(age1!=null&&age1>0, "age",age1)
                .lt(age2!=null&&age1>0, "age",age2)
                .eq(departmentId!=null&&departmentId>0, "department.id",departmentId)
                .build();
    }

    @Override
    public Pageable getPageable() {
        return new PageRequest(getJpaPageNo(), getPageSize(), getSort());
    }

    @Override
    public Sort getSort() {
        if(StringUtils.isNotBlank(getOrder())){
            this.setOrder("id");
        }
        Sort.Direction direction = Sort.Direction.ASC;
        if("desc".equalsIgnoreCase(getOrderType())){
            direction = Sort.Direction.DESC;
        }
        return new Sort(new Sort.Order(direction, getOrder()));
    }
}
