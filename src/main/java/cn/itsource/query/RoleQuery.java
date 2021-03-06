package cn.itsource.query;

import cn.itsource.domain.Role;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * (Role)Query实体类-高级查询
 *
 * @author 吴昌勇
 * @since 2019-11-11 14:06:14
 */
public class RoleQuery extends BaseQuery<Role> {
    
    private String name;
    
    //若要添加其他高级查询条件，请在这里添加字段以及setget方法
    
    /**
     * 获取高级查询规则对象
     * @return
     */
    public Specification<Role> getSpecification(){
        return Specifications.<Role>and()
                .like(StringUtils.isNotBlank(name), "name", "%"+name+"%")
                //若要添加其他高级查询条件，请在这里添加其他拼接条件的方法
                .build();
    }

    /**
     * 获取分页规则对象
     * @return
     */
    public Pageable getPageable(){
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
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}