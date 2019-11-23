package cn.itsource.easypoi;

import cn.itsource.domain.Department;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentNameNotBlankValidator implements ConstraintValidator<DepartmentNameNotBlank,Department>{
    @Override
    public void initialize(DepartmentNameNotBlank departmentNameNotBlank) {

    }

    /**
     * 自定义注解的验证规则实现逻辑（如何帮我们验证@DepartmentNameNotBlank）
     * @param department
     * @param context
     * @return true表示验证通过，false表示验证失败，就会默认提取当前注解的的message属性值
     */
    @Override
    public boolean isValid(Department department, ConstraintValidatorContext context) {
        if(department == null){
            return true;
        }
        //规避掉直接从前端表单中新增或者修改员工数据的验证失败的情况
        if(department.getId() != null && department.getId() > 0){
            return true;
        }
        return StringUtils.isNotBlank(department.getName());
    }
}
