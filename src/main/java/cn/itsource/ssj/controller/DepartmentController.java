package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Department;
import cn.itsource.ssj.query.DepartmentQuery;
import cn.itsource.ssj.service.IDepartmentService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * (Department)controller
 *
 * @author 熊峰
 * @since 2019-11-14 18:29:59
 */
@Controller
@RequestMapping("/department")
public class DepartmentController{
    @Autowired
    private IDepartmentService departmentService;
    @RequestMapping("/index")
    public String index(){
        return "department/department";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Department> page(DepartmentQuery departmentQuery){
        return departmentService.findPageByQuery(departmentQuery);
    }
    @ModelAttribute("findOneById")
    public Department findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Department department = departmentService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return department;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Department findOne(Long id){
        return departmentService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Department department){
        try {
            departmentService.save(department);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Department department){
        try {
            departmentService.update(department);
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
            departmentService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Department> findAll(DepartmentQuery departmentQuery){
        List<Department> list = departmentService.findByQuery(departmentQuery);
        list.add(0, new Department(-1L,"请选择部门"));
        return list;
    }
}