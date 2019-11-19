package cn.itsource.ssj.query;

import cn.itsource.ssj.domain.Menu;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
/**
 * (Menu)高级查询实体类
 *
 * @author 熊峰
 * @since 2019-11-12 09:37:13
 */
public class MenuQuery extends BaseQuery<Menu> {
    
    private String name; 
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public Specification createSpecification() {
        return  Specifications.<Menu>and()
                .like(StringUtils.isNotBlank(name), "name","%"+name+"%")
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