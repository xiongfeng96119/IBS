package cn.itsource.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.itsource.easypoi.DepartmentNameNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends BaseDomain{

    @NotBlank(message = "用户名不能为空！")
    @Excel(name = "用户名")
    @Column(unique = true,nullable = false)
    private String username;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "邮箱")
    private String email;
    @Excel(name = "头像")
    private String headImage;

    @Max(value = 50,message = "年龄不能超过50岁！")
    @Min(value = 18,message = "年龄不能低于18岁！")
    @Excel(name = "年龄")
    private Integer age;

    @ExcelEntity    //导出关联对象的属性
    @DepartmentNameNotBlank //使用自定义的注解来验证关联对象的属性不能为空
    @ManyToOne(fetch = FetchType.LAZY)  //JPA
    @JoinColumn(name = "department_id") //JPA
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  //Jackson
    private Department department;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "employees")
    private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", headImage='" + headImage + '\'' +
                ", age=" + age +
                '}';
    }

    public Employee() {
    }

    public Employee(Long id, String username) {
        this.setId(id);
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
