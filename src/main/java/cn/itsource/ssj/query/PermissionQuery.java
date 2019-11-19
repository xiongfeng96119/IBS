package cn.itsource.ssj.query;

import cn.itsource.ssj.domain.Permission;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
/**
 * (Permission)高级查询实体类
 *
 * @author 熊峰
 * @since 2019-11-12 09:44:40
 */
public class PermissionQuery extends BaseQuery<Permission> {
    
    private String name; 
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public Specification createSpecification() {
        return  Specifications.<Permission>and()
                .like(StringUtils.isNotBlank(name), "name","%"+name+"%")
                .build();
    }

    @Override
    public Pageable getPageable() {
        return new PageRequest(getJpaPageNo(), getPageSize(), getSort());
    }

    /**
     * 获取排序规则对象
     * @return
     */
    public Sort getSort(){
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