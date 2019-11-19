package cn.itsource.ssj.domain;

import javax.persistence.*;
/**
 * (Dept)实体类
 *
 * @author 熊峰
 * @since 2019-11-08 18:31:58
 */
@Entity
@Table(name="dept")
public class Dept extends BaseDomain{
 
     private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}