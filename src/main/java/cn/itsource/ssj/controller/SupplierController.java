package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Supplier;
import cn.itsource.ssj.query.SupplierQuery;
import cn.itsource.ssj.service.ISupplierService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * (Supplier)controller
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:30
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController{
    @Autowired
    private ISupplierService supplierService;
    @RequestMapping("/index")
    public String index(){
        return "supplier/supplier";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Supplier> page(SupplierQuery supplierQuery){
        return supplierService.findPageByQuery(supplierQuery);
    }
    @ModelAttribute("findOneById")
    public Supplier findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Supplier supplier = supplierService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return supplier;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Supplier findOne(Long id){
        return supplierService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Supplier supplier){
        try {
            supplierService.save(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Supplier supplier){
        try {
            supplierService.update(supplier);
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
            supplierService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Supplier> findAll(SupplierQuery supplierQuery){
        List<Supplier> list = supplierService.findByQuery(supplierQuery);
        list.add(0,new Supplier(-1L,"请选择供应商"));
        return list;
    }
}