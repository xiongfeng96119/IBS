package cn.itsource.ssj.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.itsource.ssj.domain.Department;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.easypoi.UniqueUsernameVerifyHandler;
import cn.itsource.ssj.service.IDepartmentService;
import cn.itsource.ssj.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private UniqueUsernameVerifyHandler uniqueUsernameVerifyHandler;


    @RequestMapping("/index")
    public String index(){
        return "employee/import";
    }

    @RequestMapping("/importEmployees")
    public String importEmployees(MultipartFile excelFile, Model model, HttpServletResponse response) throws Exception {
            //导入参数
            ImportParams params = new ImportParams();
            //文件标题占几行
            params.setTitleRows(1);
            //表头占几行
            params.setHeadRows(1);
            //是否需要导入验证
            params.setNeedVerfiy(true);
            //使用自定义验证器
            params.setVerifyHandler(uniqueUsernameVerifyHandler);

            ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(excelFile.getInputStream(), Employee.class, params);
            //验证成功的数据
            List<Employee> list = result.getList();
            list.forEach(employee -> {
                //对象不能关联临时对象，先查询出一个持久对象
                Department department = departmentService.findByName(employee.getDepartment().getName());
                employee.setDepartment(department);
                employeeService.save(employee);
            });
            //验证失败的数据
            if (result.isVerfiyFail()) {
                ServletOutputStream fos = response.getOutputStream();
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
                String fileName = new String("导入失败的数据.xlsx".getBytes("UTF-8"),"ISO-8859-1");
                response.setHeader("Content-disposition", "attachment;filename="+fileName);
                response.setHeader("Pragma", "No-cache");
                //将验证失败的数据写出到一个新建工作簿返回给用户
                result.getFailWorkbook().write(fos);
                fos.close();
            }
        return "employee/import";
    }


}
