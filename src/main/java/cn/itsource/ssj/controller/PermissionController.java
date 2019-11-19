package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Permission;
import cn.itsource.ssj.query.PermissionQuery;
import cn.itsource.ssj.service.IPermissionService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Permission)controller
 *
 * @author 熊峰
 * @since 2019-11-12 09:44:40
 */
@Controller
@RequestMapping("/permission")
public class PermissionController{
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/index")
    public String index(){
        return "permission/permission";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Permission> page(PermissionQuery permissionQuery){
        return permissionService.findPageByQuery(permissionQuery);
    }
    @ModelAttribute("findOneById")
    public Permission findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Permission permission = permissionService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return permission;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Permission findOne(Long id){
        return permissionService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Permission permission){
        try {
            permissionService.save(permission);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Permission permission){
        try {
            permissionService.update(permission);
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
            permissionService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
}