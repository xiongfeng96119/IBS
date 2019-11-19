package cn.itsource.ssj.query;

import cn.itsource.ssj.domain.Department;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentQuery extends BaseQuery<Department> {
    @Override
    public Specification createSpecification() {
        return null;
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
