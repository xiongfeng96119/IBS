package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Systemdictionarydetail;
import cn.itsource.ssj.query.SystemdictionarydetailQuery;
import cn.itsource.ssj.service.ISystemdictionarydetailService;
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
 * (Systemdictionarydetail)controller
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:31
 */
@Controller
@RequestMapping("/systemdictionarydetail")
public class SystemdictionarydetailController{
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;
    @RequestMapping("/index")
    public String index(){
        return "systemdictionarydetail/systemdictionarydetail";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Systemdictionarydetail> page(SystemdictionarydetailQuery systemdictionarydetailQuery){
        return systemdictionarydetailService.findPageByQuery(systemdictionarydetailQuery);
    }
    @ModelAttribute("findOneById")
    public Systemdictionarydetail findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Systemdictionarydetail systemdictionarydetail = systemdictionarydetailService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return systemdictionarydetail;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Systemdictionarydetail findOne(Long id){
        return systemdictionarydetailService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Systemdictionarydetail systemdictionarydetail){
        try {
            systemdictionarydetailService.save(systemdictionarydetail);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Systemdictionarydetail systemdictionarydetail){
        try {
            systemdictionarydetailService.update(systemdictionarydetail);
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
            systemdictionarydetailService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
    @RequestMapping("/findUnit")
    @ResponseBody
    public List<Systemdictionarydetail> findUnit(){
        return systemdictionarydetailService.findUnit();
    }
    @RequestMapping("/findBrand")
    @ResponseBody
    public List<Systemdictionarydetail> findBrand(){
        return systemdictionarydetailService.findBrand();
    }
}