package cn.itsource.easypoi;

import cn.itsource.domain.Department;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//validatedBy 表示指定一个当前自定义注解验证器的验证逻辑
@Constraint(validatedBy = DepartmentNameNotBlankValidator.class)
public @interface DepartmentNameNotBlank {

    /**
     * 验证不通过时的错误提示信息
     *
     * @return
     */
    String message() default "员工所属部门不能为空！";

    Class<?>[] groups() default {};

    /**
     * 变量名称 payload不可变
     * 否则会抛出异常`javax.validation.ConstraintDefinitionException: HV000074`
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

}
