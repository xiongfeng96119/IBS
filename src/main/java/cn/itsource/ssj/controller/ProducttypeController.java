package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Producttype;
import cn.itsource.ssj.query.ProducttypeQuery;
import cn.itsource.ssj.service.IProducttypeService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * (Producttype)controller
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:29
 */
@Controller
@RequestMapping("/producttype")
public class ProducttypeController{
    @Autowired
    private IProducttypeService producttypeService;
    @RequestMapping("/index")
    public String index(){
        return "producttype/producttype";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Producttype> page(ProducttypeQuery producttypeQuery){
        return producttypeService.findPageByQuery(producttypeQuery);
    }
    @ModelAttribute("findOneById")
    public Producttype findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Producttype producttype = producttypeService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return producttype;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Producttype findOne(Long id){
        return producttypeService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Producttype producttype){
        try {
            if (producttype.getParent().getId()==null){
                producttype.setParent(null);
            }else{
                Producttype producttype1 = producttypeService.findOne(producttype.getParent().getId());
                producttype.setParent(producttype1);
            }
            producttypeService.save(producttype);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Producttype producttype){
        try {
            producttypeService.update(producttype);
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
            producttypeService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
    @RequestMapping("/findAllParent")
    @ResponseBody
    public List<Producttype> findAllParent(){
        List<Producttype> list = producttypeService.findAllParent();
        return list;
    }
    @RequestMapping("/findAllChildren")
    @ResponseBody
    public List<Producttype> findAllChildren(){
        List<Producttype> list = producttypeService.findAllChildren();
        return list;
    }
}