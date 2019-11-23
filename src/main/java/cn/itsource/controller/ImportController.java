package cn.itsource.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.itsource.domain.Department;
import cn.itsource.domain.Employee;
import cn.itsource.easypoi.UniqueExcelVerifyHandler;
import cn.itsource.service.IDepartmentService;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private UniqueExcelVerifyHandler verifyHandler;

    @RequestMapping("/index")
    public String index(){
        return "employee/import";
    }

    //@ResponseBody
    @RequestMapping("/importEmployees")
    public String importEmployees(MultipartFile excelFile, HttpServletResponse response) throws Exception {
        /**
         * 导入参数
         *  titleRows标题占几行
         *  headRows表头占几行
         */
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setNeedVerfiy(true);     //设置是否需要导入验证
        params.setVerifyHandler(verifyHandler);     //设置自定义的验证规则（只能设置一个）
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(excelFile.getInputStream(),
                Employee.class, params);
        //验证成功的数据
        result.getList().forEach(employee -> {
            //通过部门名称去查询一个部门对象，解决对象不能关联临时对象的错误
            Department department = departmentService.findByName(employee.getDepartment().getName());
            employee.setDepartment(department);
            employeeService.save(employee);
//            System.out.println("成功的："+employee);
        });
        //验证失败的数据
        //result.getFailList().forEach(employee -> System.out.println("失败的："+employee));
        if (result.isVerfiyFail()) { //验证是否失败(把失败的xlsx返回给用户)
            ServletOutputStream fos = response.getOutputStream();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=errorx.xlsx");
            response.setHeader("Pragma", "No-cache");
            //验证失败的数据直接获取工作簿，写出到响应的输出流中，相当于一个文件下载
            result.getFailWorkbook().write(fos);
            fos.close();
        }
        return "employee/import";
    }


}
