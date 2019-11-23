package cn.itsource.test.poi;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;

/**
 * @ExcelTarget("emp")表示将当前domain实体类添加一个命名空间，一般用在有关联关系的多个domain之间
 * 然后要达到导出不同domain实体类数据的时候显示不同的列名称，以及有些列显示与否，则可以在@Excel注解的name属性中使用
 * @Excel(name = "所属部门_emp,部门名称_dept")
 */
@ExcelTarget("emp")
public class PoiEmployee {

    private Long id;

    @Excel(name = "头像",type = 2,width = 10, height = 20,savePath="upload")
    private String headImage = "16.jpg";

    /**
     * @Excel注解在导出数据的时候是必写的，如果domain实体类中没有写过这个注解则要报错
     *  name表示导出到excel文件中后的表头名称
     *  width表示指定列的宽度，默认是10
     *  height表示行高，默认是10
     *  replace表示将值进行替换
     *      男_1 表示将整数1替换成“男”
     *      女_2 表示将整数2替换成“女”
     *  format表示格式化日期，填的值就是日期格式
     *  type表示导出的数据类型
     *      导出类型 1是文本 2是图片,3是函数,10是数字 默认是文本
     *  savePath表示导入数据时包含图片文件（相当于做了一个文件上传）上传到服务器端的保存路径
     *  如果@Excel注解的字段是一个对象类型，则会自动调用它的toString方法得到字符串!!!
     *  如果你不想让它使用toString方法，就在该字段上面加@ExcelEntity注解，然后在关联对象类中某个或者某些字段
     *  上面再加@Excel注解（name属性是必写的）
     */
    @Excel(name = "姓名")
    private String name;

    @Excel(name = "邮箱",width = 30,height = 10)
    private String email;

    @Excel(name = "年龄")
    private Integer age;

    @ExcelEntity
    private PoiDept dept;

    @Excel(name = "性别",replace={"男_1","女_2"})
    private Integer gender;

    @Excel(name = "生日",format = "yyyy-MM-dd")
    private Date birthDay = new Date();

    @Override
    public String toString() {
        return "PoiEmployee{" +
                "id=" + id +
                ", headImage='" + headImage + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", dept=" + dept +
                ", gender=" + gender +
                ", birthDay=" + birthDay +
                '}';
    }

    public PoiEmployee() {
    }

    public PoiEmployee(String name, String email, Integer age, Integer gender) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PoiDept getDept() {
        return dept;
    }

    public void setDept(PoiDept dept) {
        this.dept = dept;
    }
}
