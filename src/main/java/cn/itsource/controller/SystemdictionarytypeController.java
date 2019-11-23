package cn.itsource.controller;

import cn.itsource.domain.Systemdictionarytype;
import cn.itsource.query.SystemdictionarytypeQuery;
import cn.itsource.service.ISystemdictionarytypeService;
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
 * (Systemdictionarytype)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:21:10
 */
@Controller
@RequestMapping("/systemdictionarytype")
public class SystemdictionarytypeController {
    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;
    
    @RequestMapping("/index")
    public String index(){
        return "systemdictionarytype/systemdictionarytype";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Systemdictionarytype> page(SystemdictionarytypeQuery systemdictionarytypeQuery){
        return systemdictionarytypeService.findByPage(systemdictionarytypeQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Systemdictionarytype systemdictionarytype){
        try {
            systemdictionarytypeService.save(systemdictionarytype);
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
    @ModelAttribute("updateSystemdictionarytype")
    public Systemdictionarytype findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Systemdictionarytype systemdictionarytype = systemdictionarytypeService.findOne(id);
            //让员工与它关联的部门对象断开关系 解决N to N错误
            //employee.setDepartment(null);
            return systemdictionarytype;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param systemdictionarytype
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateSystemdictionarytype")Systemdictionarytype systemdictionarytype){
        try {
            systemdictionarytypeService.update(systemdictionarytype);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Systemdictionarytype findOne(Long id){
        return systemdictionarytypeService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            systemdictionarytypeService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    @ResponseBody
    @RequestMapping("/findAllDictionaryTypes")
    public List<Systemdictionarytype> findAllDictionaryTypes(SystemdictionarytypeQuery systemdictionarytypeQuery){
        List<Systemdictionarytype> list = systemdictionarytypeService.findAll(systemdictionarytypeQuery);
        list.add(0, new Systemdictionarytype(-1L,"请选择数据字典类型"));
        return list;
    }
}