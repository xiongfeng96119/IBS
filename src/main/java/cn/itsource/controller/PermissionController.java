package cn.itsource.controller;

import cn.itsource.domain.Permission;
import cn.itsource.query.PermissionQuery;
import cn.itsource.service.IPermissionService;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Permission)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-11 14:06:13
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    
    @RequestMapping("/index")
    public String index(){
        return "permission/permission";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Permission> page(PermissionQuery permissionQuery){
        return permissionService.findByPage(permissionQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Permission permission){
        try {
            if(permission.getMenu().getId() == -1){
                throw new Exception("权限关联的菜单不能为空！");
            }
            permissionService.save(permission);
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
    @ModelAttribute("updatePermission")
    public Permission findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Permission permission = permissionService.findOne(id);
            //解决N to N错误
            permission.setMenu(null);
            return permission;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param permission
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updatePermission")Permission permission){
        try {
            if(permission.getMenu().getId() == -1){
                throw new Exception("权限关联的菜单不能为空！");
            }
            permissionService.update(permission);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Permission findOne(Long id){
        return permissionService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            permissionService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }
}