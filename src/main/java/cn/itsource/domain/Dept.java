package cn.itsource.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Dept)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-08 14:51:36
 */
@Entity
@Table(name = "dept")
public class Dept extends BaseDomain {

        
    private String name;

    @Override
    public String toString() {
        return "Dept{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}