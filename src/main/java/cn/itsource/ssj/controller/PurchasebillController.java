package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Purchasebill;
import cn.itsource.ssj.domain.Purchasebillitem;
import cn.itsource.ssj.query.PurchasebillQuery;
import cn.itsource.ssj.service.IPurchasebillService;
import cn.itsource.ssj.shiro.SessionUtils;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * (Purchasebill)controller
 *
 * @author 熊峰
 * @since 2019-11-18 10:22:57
 */
@Controller
@RequestMapping("/purchasebill")
public class PurchasebillController{
    @Autowired
    private IPurchasebillService purchasebillService;
    @RequestMapping("/index")
    public String index(){
        return "purchasebill/purchasebill";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Purchasebill> page(PurchasebillQuery purchasebillQuery){
        return purchasebillService.findPageByQuery(purchasebillQuery);
    }
    @ModelAttribute("findOneById")
    public Purchasebill findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Purchasebill purchasebill = purchasebillService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //把有关系的对象移除
            purchasebill.setSupplier(null);
            purchasebill.setBuyer(null);
            purchasebill.getItems().clear();
            return purchasebill;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Purchasebill findOne(Long id){
        return purchasebillService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Purchasebill purchasebill){
        try {
            if (purchasebill.getBuyer().getId()<=0){
                throw new Exception("必须选择采购员");
            }
            if (purchasebill.getSupplier().getId()<=0){
                throw new Exception("必须选择供应商");
            }
            //设置录入人为当前登录用户
            purchasebill.setInputuser(SessionUtils.getUser());
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal totalNum = new BigDecimal(0);
            for (Purchasebillitem item : purchasebill.getItems()) {
                //组合关系要求一方能找到多方，多方也能找到一方
                item.setBill(purchasebill);
                item.setAmount(item.getPrice().multiply(item.getNum()));
                //计算出采购的所有商品的数量
                totalNum = totalNum.add(item.getNum());
                //计算采购的所有商品的总金额
                totalAmount = totalAmount.add(item.getAmount());
            }
            purchasebill.setTotalnum(totalNum);
            purchasebill.setTotalamount(totalAmount);
            purchasebillService.save(purchasebill);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Purchasebill purchasebill){
        try {
            purchasebillService.update(purchasebill);
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
            purchasebillService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }

}