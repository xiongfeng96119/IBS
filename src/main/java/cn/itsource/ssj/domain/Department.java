package cn.itsource.ssj.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department extends BaseDomain{
    @Excel(name = "所属部门")
    private String name;

    public Department() {
    }

    public Department(Long id , String name) {
        this.setId(id);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
