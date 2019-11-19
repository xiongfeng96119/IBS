package cn.itsource.ssj.query;

import java.math.BigDecimal;
import cn.itsource.ssj.domain.Purchasebillitem;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
/**
 * (Purchasebillitem)高级查询实体类
 *
 * @author 熊峰
 * @since 2019-11-18 10:22:59
 */
public class PurchasebillitemQuery extends BaseQuery<Purchasebillitem> {
    
    private String name; 
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public Specification createSpecification() {
        return  Specifications.<Purchasebillitem>and()
                .like(StringUtils.isNotBlank(name), "name","%"+name+"%")
                .build();
    }
    /**
     * 获取分页规则对象
     * @return
     */
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