package cn.itsource.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.service.IEmployeeService;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Employee> page(EmployeeQuery employeeQuery){
        return employeeService.findByPage(employeeQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Employee employee){
        try {
            employeeService.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"新增失败：" + e.getMessage());
        }
        return new ResultJson(200,"新增成功！");
    }

    /**
     * @ModelAttribute注解表示当前类的其他所有方法执行之前先执行此方法
     * @param id
     * @return
     */
    @ModelAttribute("updateEmployee")
    public Employee findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Employee employee = employeeService.findOne(id);
            //让员工与它关联的部门对象断开关系
            employee.setDepartment(null);
           return employee;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateEmployee")Employee employee){
        try {
            employeeService.update(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Employee findOne(Long id){
        return employeeService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            employeeService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    @ResponseBody
    @RequestMapping("/checkUsername")
    public boolean checkUsername(String username, Long id){
        return employeeService.checkUsername(username, id);
    }

    @RequestMapping("/export")
    public String export(EmployeeQuery employeeQuery, ModelMap map){
        ExportParams params = new ExportParams("员工列表", "员工列表", ExcelType.XSSF);
        //params.setFreezeCol(2);
        //根据高级查询条件查询到要导出的数据集合
        List<Employee> list = employeeService.findAll(employeeQuery);
        map.put(NormalExcelConstants.DATA_LIST, list);              // 数据集合
        map.put(NormalExcelConstants.CLASS, Employee.class);        //导出实体
        map.put(NormalExcelConstants.PARAMS, params);               //导出参数
        map.put(NormalExcelConstants.FILE_NAME, "员工列表");         //文件名称
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;             //View名称
    }

    @ResponseBody
    @RequestMapping("/findAllBuyer")
    public List<Employee> findAllBuyer(EmployeeQuery employeeQuery){
        employeeQuery.setDepartmentId(2L);//采购部的员工都是采购员
        List<Employee> list = employeeService.findAll(employeeQuery);
        list.add(0, new Employee(-1L,"请选择采购员"));
        return list;
    }


    @ResponseBody
    @RequestMapping("/findAllKeeper")
    public List<Employee> findAllKeeper(EmployeeQuery employeeQuery){
        employeeQuery.setDepartmentId(4L);//仓管部的员工都是库管员
        List<Employee> list = employeeService.findAll(employeeQuery);
        list.add(0, new Employee(-1L,"请选择库管员"));
        return list;
    }

}
