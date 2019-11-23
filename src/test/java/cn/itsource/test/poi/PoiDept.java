package cn.itsource.test.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("dept")
public class PoiDept {

    @Excel(name = "编号_dept")
    private Long id;
    @Excel(name = "所属部门_emp,部门名称_dept")
    private String name;

    public PoiDept() {
    }

    public PoiDept(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PoiDept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
