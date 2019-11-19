package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Purchasebillitem;
import cn.itsource.ssj.query.PurchasebillitemQuery;
import cn.itsource.ssj.service.IPurchasebillitemService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Purchasebillitem)controller
 *
 * @author 熊峰
 * @since 2019-11-18 10:22:59
 */
@Controller
@RequestMapping("/purchasebillitem")
public class PurchasebillitemController{
    @Autowired
    private IPurchasebillitemService purchasebillitemService;
    @RequestMapping("/index")
    public String index(){
        return "purchasebillitem/purchasebillitem";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Purchasebillitem> page(PurchasebillitemQuery purchasebillitemQuery){
        return purchasebillitemService.findPageByQuery(purchasebillitemQuery);
    }
    @ModelAttribute("findOneById")
    public Purchasebillitem findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Purchasebillitem purchasebillitem = purchasebillitemService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return purchasebillitem;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Purchasebillitem findOne(Long id){
        return purchasebillitemService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Purchasebillitem purchasebillitem){
        try {
            purchasebillitemService.save(purchasebillitem);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Purchasebillitem purchasebillitem){
        try {
            purchasebillitemService.update(purchasebillitem);
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
            purchasebillitemService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
}