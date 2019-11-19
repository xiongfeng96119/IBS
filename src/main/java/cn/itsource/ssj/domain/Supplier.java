package cn.itsource.ssj.domain;

import javax.persistence.*;
/**
 * (Supplier)domain实体类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:30
 */
@Entity
@Table(name="supplier")
public class Supplier extends BaseDomain{
 
    private String name;

    public Supplier() {
    }

    public Supplier(Long id , String name) {
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