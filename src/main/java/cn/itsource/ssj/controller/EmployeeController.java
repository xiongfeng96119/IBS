package cn.itsource.ssj.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.EmployeeQuery;
import cn.itsource.ssj.service.IEmployeeService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @RequestMapping("/index")
    public String index(){
        return "employee/employee";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Employee> page(EmployeeQuery employeeQuery){
        return employeeService.findPageByQuery(employeeQuery);
    }
    @ModelAttribute("findOneById")
    public Employee findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Employee employee = employeeService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            employee.setDepartment(null);
            return employee;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Employee findOne(Long id){
        return employeeService.findOne(id);
    }

    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Employee employee){
        try {
            employeeService.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Employee employee){
        try {
            employeeService.update(employee);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"修改失败：" + e.getMessage());
        }
        return new Result(200, "修改成功！");
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(String ids){
        try {
            employeeService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
    @ResponseBody
    @RequestMapping("/checkUsername")
    public boolean checkUsername(String username,Long id){
        return employeeService.checkUsername(username, id);
    }
    @RequestMapping("/export")
    public String export(EmployeeQuery employeeQuery, ModelMap map, HttpServletRequest req){
        //导出参数
        ExportParams params = new ExportParams("员工列表", "员工数据", ExcelType.XSSF);
        //根据高级查询条件查询需要导出的数据
        List<Employee> list = employeeService.findByQuery(employeeQuery);
        //数据集合
        map.put(NormalExcelConstants.DATA_LIST, list);
        //导出实体
        map.put(NormalExcelConstants.CLASS, Employee.class);
        //导出参数
        map.put(NormalExcelConstants.PARAMS, params);
        //文件名称
        map.put(NormalExcelConstants.FILE_NAME, "员工列表");
        //View名称  EasypoiSingleExcelView
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;
    }
    @ResponseBody
    @RequestMapping("/findAllBuyer")
    public List<Employee> findAllBuyer(){
        List<Employee> list = employeeService.findAllBuyer();
        list.add(0,new Employee(-1L,"请选择采购员"));
        return list;
    }
}
