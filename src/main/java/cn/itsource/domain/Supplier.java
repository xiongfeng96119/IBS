package cn.itsource.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Supplier)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:58
 */
@Entity
@Table(name = "supplier")
public class Supplier extends BaseDomain {

        
    private String name;

    public Supplier() {
    }

    public Supplier(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}