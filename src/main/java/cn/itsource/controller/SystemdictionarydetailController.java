package cn.itsource.controller;

import cn.itsource.domain.Systemdictionarydetail;
import cn.itsource.query.SystemdictionarydetailQuery;
import cn.itsource.service.ISystemdictionarydetailService;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * (Systemdictionarydetail)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:21:09
 */
@Controller
@RequestMapping("/systemdictionarydetail")
public class SystemdictionarydetailController {
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;
    
    @RequestMapping("/index")
    public String index(){
        return "systemdictionarydetail/systemdictionarydetail";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Systemdictionarydetail> page(SystemdictionarydetailQuery systemdictionarydetailQuery){
        return systemdictionarydetailService.findByPage(systemdictionarydetailQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Systemdictionarydetail systemdictionarydetail){
        try {
            if(systemdictionarydetail.getTypes() == null || systemdictionarydetail.getTypes().getId() == -1){
                throw new Exception("关联的数据字典类型不能为空！");
            }
            systemdictionarydetailService.save(systemdictionarydetail);
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
    @ModelAttribute("updateSystemdictionarydetail")
    public Systemdictionarydetail findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Systemdictionarydetail systemdictionarydetail = systemdictionarydetailService.findOne(id);
            //让员工与它关联的部门对象断开关系 解决N to N错误
            //employee.setDepartment(null);
            return systemdictionarydetail;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param systemdictionarydetail
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateSystemdictionarydetail")Systemdictionarydetail systemdictionarydetail){
        try {
            if(systemdictionarydetail.getTypes() == null || systemdictionarydetail.getTypes().getId() == -1){
                throw new Exception("关联的数据字典类型不能为空！");
            }
            systemdictionarydetailService.update(systemdictionarydetail);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Systemdictionarydetail findOne(Long id){
        return systemdictionarydetailService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            systemdictionarydetailService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    @ResponseBody
    @RequestMapping("/findDictionaryDetails")
    public List<Systemdictionarydetail> findDictionaryDetails(String sn){
        return systemdictionarydetailService.findDictionaryDetails(sn);
    }
}