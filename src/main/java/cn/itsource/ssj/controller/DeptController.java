package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Dept;
import cn.itsource.ssj.query.DeptQuery;
import cn.itsource.ssj.service.IDeptService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Dept)controller
 *
 * @author 熊峰
 * @since 2019-11-08 19:39:20
 */
@Controller
@RequestMapping("/dept")
public class DeptController{
    @Autowired
    private IDeptService deptService;
    @RequestMapping("/index")
    public String index(){
        return "dept/dept";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Dept> page(DeptQuery deptQuery){
        return deptService.findPageByQuery(deptQuery);
    }
    @ModelAttribute("findOneById")
    public Dept findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Dept dept = deptService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return dept;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Dept findOne(Long id){
        return deptService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Dept dept){
        try {
            deptService.save(dept);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Dept dept){
        try {
            deptService.update(dept);
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
            deptService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
}