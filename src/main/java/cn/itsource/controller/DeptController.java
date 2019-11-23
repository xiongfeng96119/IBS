package cn.itsource.controller;

import cn.itsource.domain.Dept;
import cn.itsource.query.DeptQuery;
import cn.itsource.service.IDeptService;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Dept)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-08 15:52:48
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private IDeptService deptService;
    
    @RequestMapping("/index")
    public String index(){
        return "dept/dept";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Dept> page(DeptQuery deptQuery){
        return deptService.findByPage(deptQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Dept dept){
        try {
            deptService.save(dept);
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
    @ModelAttribute("updateDept")
    public Dept findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Dept dept = deptService.findOne(id);
            //让员工与它关联的部门对象断开关系 解决N to N错误
            //employee.setDepartment(null);
            return dept;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateDept")Dept dept){
        try {
            deptService.update(dept);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Dept findOne(Long id){
        return deptService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            deptService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }
}