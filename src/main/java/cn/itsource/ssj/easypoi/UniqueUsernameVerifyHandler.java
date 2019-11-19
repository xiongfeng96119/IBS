package cn.itsource.ssj.easypoi;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义验证用户名唯一
 */
@Component
public class UniqueUsernameVerifyHandler implements IExcelVerifyHandler<Employee> {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult();
        Employee employee1 = employeeService.findByUsername(employee.getUsername());
        if (employee1 != null){
            result.setSuccess(false);
            result.setMsg("用户名：\""+employee.getUsername()+"\"已被占用!");
        }else {
            result.setSuccess(true);
        }
        return result;
    }
}
