package cn.itsource.controller;

import java.math.BigDecimal;
import java.util.Date;
import cn.itsource.domain.Purchasebill;
import cn.itsource.domain.Purchasebillitem;
import cn.itsource.query.PurchasebillQuery;
import cn.itsource.service.IPurchasebillService;
import cn.itsource.shiro.SessionUtils;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Purchasebill)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:56
 */
@Controller
@RequestMapping("/purchasebill")
public class PurchasebillController {
    @Autowired
    private IPurchasebillService purchasebillService;
    
    @RequestMapping("/index")
    public String index(){
        return "purchasebill/purchasebill";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Purchasebill> page(PurchasebillQuery purchasebillQuery){
        return purchasebillService.findByPage(purchasebillQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Purchasebill purchasebill){
        try {
            if(purchasebill.getBuyer().getId() <= 0){
                throw new Exception("必须选择采购员！");
            }
            if(purchasebill.getSupplier().getId() <= 0){
                throw new Exception("必须选择供应商！");
            }
            purchasebill.setInputuser(SessionUtils.getUser());  //设置录入人
            //采购单总金额和总数量
            BigDecimal totalNum = new BigDecimal(0);
            BigDecimal totalAmount = new BigDecimal(0);
            for (Purchasebillitem item : purchasebill.getBillitems()) {
                //每一个Purchasebillitem的bill属性不能为空（外键不能为空）组合关系就要求一方能够找到多方，多方也要能够找到一方
                item.setBill(purchasebill);
                item.setAmount(item.getPrice().multiply(item.getNum()));    //计算采购单明细的小计金额
                //统计采购单中所有采购商品的总数量
                totalNum = totalNum.add(item.getNum());
                //统计采购单中所有采购商品的总金额
                totalAmount = totalAmount.add(item.getAmount());
            }
            purchasebill.setTotalamount(totalAmount);
            purchasebill.setTotalnum(totalNum);
            purchasebillService.save(purchasebill);
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
    @ModelAttribute("updatePurchasebill")
    public Purchasebill findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Purchasebill purchasebill = purchasebillService.findOne(id);
            //解决N to N错误
            purchasebill.setSupplier(null);
            purchasebill.setBuyer(null);
            purchasebill.getBillitems().clear();
            return purchasebill;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param purchasebill
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updatePurchasebill")Purchasebill purchasebill){
        try {
            if(purchasebill.getBuyer().getId() <= 0){
                throw new Exception("必须选择采购员！");
            }
            if(purchasebill.getSupplier().getId() <= 0){
                throw new Exception("必须选择供应商！");
            }
            //采购单总金额和总数量
            BigDecimal totalNum = new BigDecimal(0);
            BigDecimal totalAmount = new BigDecimal(0);
            for (Purchasebillitem item : purchasebill.getBillitems()) {
                //每一个Purchasebillitem的bill属性不能为空（外键不能为空）组合关系就要求一方能够找到多方，多方也要能够找到一方
                item.setBill(purchasebill);
                item.setAmount(item.getPrice().multiply(item.getNum()));    //计算采购单明细的小计金额
                //统计采购单中所有采购商品的总数量
                totalNum = totalNum.add(item.getNum());
                //统计采购单中所有采购商品的总金额
                totalAmount = totalAmount.add(item.getAmount());
            }
            purchasebill.setTotalamount(totalAmount);
            purchasebill.setTotalnum(totalNum);
            purchasebillService.update(purchasebill);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Purchasebill findOne(Long id){
        return purchasebillService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            purchasebillService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }
}