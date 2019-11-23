package cn.itsource.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Department)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-08 14:52:21
 */
@Entity
@Table(name = "department")
public class Department extends BaseDomain {


    @Excel(name = "所属部门")
    private String name;

    public Department() {
    }

    public Department(Long id, String name) {
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