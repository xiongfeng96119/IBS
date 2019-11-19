package cn.itsource.ssj.query;

import cn.itsource.ssj.domain.Producttype;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
/**
 * (Producttype)高级查询实体类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:29
 */
public class ProducttypeQuery extends BaseQuery<Producttype> {
    
    private String name; 
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public Specification createSpecification() {
        return  Specifications.<Producttype>and()
                .like(StringUtils.isNotBlank(name), "name","%"+name+"%")
                .build();
    }

    @Override
    public Pageable getPageable() {
        return new PageRequest(getJpaPageNo(), getPageSize(), getSort());
    }

    @Override
    public Sort getSort() {
        return null;
    }
}