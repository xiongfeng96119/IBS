package cn.itsource.controller;

import cn.itsource.domain.Producttype;
import cn.itsource.query.ProducttypeQuery;
import cn.itsource.service.IProducttypeService;
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
 * (Producttype)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:27:18
 */
@Controller
@RequestMapping("/producttype")
public class ProducttypeController {
    @Autowired
    private IProducttypeService producttypeService;
    
    @RequestMapping("/index")
    public String index(){
        return "producttype/producttype";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Producttype> page(ProducttypeQuery producttypeQuery){
        return producttypeService.findByPage(producttypeQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Producttype producttype){
        try {
            if(producttype.getParent().getId() == -1){
                producttype.setParent(null);
            }
            producttypeService.save(producttype);
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
    @ModelAttribute("updateProducttype")
    public Producttype findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Producttype producttype = producttypeService.findOne(id);
            //解决N to N错误
            producttype.setParent(null);
            return producttype;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param producttype
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateProducttype")Producttype producttype){
        try {
            if(producttype.getParent().getId() == -1){
                producttype.setParent(null);
            }
            producttypeService.update(producttype);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Producttype findOne(Long id){
        return producttypeService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            producttypeService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    /**
     *
     * @param loadParent 为true表示查询父级类型，false表示查询子类型
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public List<Producttype> findAll(Boolean loadParent){
        return producttypeService.findAll(loadParent);
    }
}