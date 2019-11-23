package cn.itsource.easypoi;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.itsource.domain.Employee;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义的验证规则：验证用户名唯一
 *  泛型填需要验证的domain实体类名称
 */
@Component
public class UniqueExcelVerifyHandler implements IExcelVerifyHandler<Employee> {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult();
        Employee employee01 = employeeService.findByUsername(employee.getUsername());
        if(employee01 != null){
            result.setSuccess(false);
            result.setMsg("用户名:\""+employee.getUsername()+"\"已被占用！");
        }else{
            result.setSuccess(true);
        }
        return result;
    }
}
