package cn.itsource.controller;

import java.math.BigDecimal;
import java.util.List;

import cn.itsource.domain.Depot;
import cn.itsource.query.DepotQuery;
import cn.itsource.service.IDepotService;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Depot)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:54
 */
@Controller
@RequestMapping("/depot")
public class DepotController {
    @Autowired
    private IDepotService depotService;
    
    @RequestMapping("/index")
    public String index(){
        return "depot/depot";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Depot> page(DepotQuery depotQuery){
        return depotService.findByPage(depotQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Depot depot){
        try {
            depotService.save(depot);
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
    @ModelAttribute("updateDepot")
    public Depot findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Depot depot = depotService.findOne(id);
            //让员工与它关联的部门对象断开关系 解决N to N错误
            //employee.setDepartment(null);
            return depot;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param depot
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateDepot")Depot depot){
        try {
            depotService.update(depot);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Depot findOne(Long id){
        return depotService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            depotService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    @ResponseBody
    @RequestMapping("/findAll")
    public List<Depot> findAll(DepotQuery depotQuery){
        List<Depot> list = depotService.findAll(depotQuery);
        list.add(0, new Depot(-1L,"请选择仓库"));
        return list;
    }
}