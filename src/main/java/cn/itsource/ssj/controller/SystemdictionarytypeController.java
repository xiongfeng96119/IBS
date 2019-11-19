package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Systemdictionarytype;
import cn.itsource.ssj.query.SystemdictionarytypeQuery;
import cn.itsource.ssj.service.ISystemdictionarytypeService;
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
 * (Systemdictionarytype)controller
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:32
 */
@Controller
@RequestMapping("/systemdictionarytype")
public class SystemdictionarytypeController{
    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;
    @RequestMapping("/index")
    public String index(){
        return "systemdictionarytype/systemdictionarytype";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Systemdictionarytype> page(SystemdictionarytypeQuery systemdictionarytypeQuery){
        return systemdictionarytypeService.findPageByQuery(systemdictionarytypeQuery);
    }
    @ModelAttribute("findOneById")
    public Systemdictionarytype findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Systemdictionarytype systemdictionarytype = systemdictionarytypeService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return systemdictionarytype;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Systemdictionarytype findOne(Long id){
        return systemdictionarytypeService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Systemdictionarytype systemdictionarytype){
        try {
            systemdictionarytypeService.save(systemdictionarytype);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Systemdictionarytype systemdictionarytype){
        try {
            systemdictionarytypeService.update(systemdictionarytype);
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
            systemdictionarytypeService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Systemdictionarytype> findAll(){
        List<Systemdictionarytype> list = systemdictionarytypeService.findAll();
        return list;
    }
}