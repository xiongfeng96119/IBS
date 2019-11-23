package cn.itsource.controller;

import cn.itsource.domain.Role;
import cn.itsource.query.RoleQuery;
import cn.itsource.service.IRoleService;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Role)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-11 14:06:14
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    
    @RequestMapping("/index")
    public String index(){
        return "role/role";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Role> page(RoleQuery roleQuery){
        return roleService.findByPage(roleQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Role role){
        try {
            roleService.save(role);
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
    @ModelAttribute("updateRole")
    public Role findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Role role = roleService.findOne(id);
            //解决N to N错误
            role.getPermissions().clear();
            //role.setPermissions(null);
            return role;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateRole")Role role){
        try {
            roleService.update(role);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Role findOne(Long id){
        return roleService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            roleService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }
}