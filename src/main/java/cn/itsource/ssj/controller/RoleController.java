package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Role;
import cn.itsource.ssj.query.RoleQuery;
import cn.itsource.ssj.service.IRoleService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Role)controller
 *
 * @author 熊峰
 * @since 2019-11-12 09:43:42
 */
@Controller
@RequestMapping("/role")
public class RoleController{
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/index")
    public String index(){
        return "role/role";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Role> page(RoleQuery roleQuery){
        return roleService.findPageByQuery(roleQuery);
    }
    @ModelAttribute("findOneById")
    public Role findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Role role = roleService.findOne(id);
            role.getPermissions().clear();
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return role;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Role findOne(Long id){
        return roleService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Role role){
        try {
            roleService.save(role);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Role role){
        try {
            roleService.update(role);
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
            roleService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
}