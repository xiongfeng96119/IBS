package cn.itsource.controller;

import cn.itsource.domain.Supplier;
import cn.itsource.query.SupplierQuery;
import cn.itsource.service.ISupplierService;
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
 * (Supplier)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:25:01
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;
    
    @RequestMapping("/index")
    public String index(){
        return "supplier/supplier";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Supplier> page(SupplierQuery supplierQuery){
        return supplierService.findByPage(supplierQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Supplier supplier){
        try {
            supplierService.save(supplier);
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
    @ModelAttribute("updateSupplier")
    public Supplier findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Supplier supplier = supplierService.findOne(id);
            //让员工与它关联的部门对象断开关系 解决N to N错误
            //employee.setDepartment(null);
            return supplier;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param supplier
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateSupplier")Supplier supplier){
        try {
            supplierService.update(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Supplier findOne(Long id){
        return supplierService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            supplierService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    @ResponseBody
    @RequestMapping("/findAll")
    public List<Supplier> findAll(SupplierQuery supplierQuery){
        List<Supplier> list = supplierService.findAll(supplierQuery);
        list.add(0, new Supplier(-1L,"请选择供应商"));
        return list;
    }
}