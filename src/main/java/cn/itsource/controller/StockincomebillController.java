package cn.itsource.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import cn.itsource.domain.Productstock;
import cn.itsource.domain.Purchasebillitem;
import cn.itsource.domain.Stockincomebill;
import cn.itsource.domain.Stockincomebillitem;
import cn.itsource.query.StockincomebillQuery;
import cn.itsource.service.IProductstockService;
import cn.itsource.service.IStockincomebillService;
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
 * (Stockincomebill)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:57
 */
@Controller
@RequestMapping("/stockincomebill")
public class StockincomebillController {
    @Autowired
    private IStockincomebillService stockincomebillService;

    @Autowired
    private IProductstockService productstockService;
    
    @RequestMapping("/index")
    public String index(){
        return "stockincomebill/stockincomebill";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Stockincomebill> page(StockincomebillQuery stockincomebillQuery){
        return stockincomebillService.findByPage(stockincomebillQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Stockincomebill stockincomebill){
        try {
            if(stockincomebill.getKeeper().getId() <= 0){
                throw new Exception("必须选择库管员！");
            }
            if(stockincomebill.getSupplier().getId() <= 0){
                throw new Exception("必须选择供应商！");
            }
            if(stockincomebill.getDepot().getId() <= 0){
                throw new Exception("必须选择仓库！");
            }
            stockincomebill.setInputuser(SessionUtils.getUser());  //设置录入人
            //采购单总金额和总数量
            BigDecimal totalNum = new BigDecimal(0);
            BigDecimal totalAmount = new BigDecimal(0);
            for (Stockincomebillitem item : stockincomebill.getBillitems()) {
                //每一个Purchasebillitem的bill属性不能为空（外键不能为空）组合关系就要求一方能够找到多方，多方也要能够找到一方
                item.setBill(stockincomebill);
                item.setAmount(item.getPrice().multiply(item.getNum()));    //计算采购单明细的小计金额
                //统计采购单中所有采购商品的总数量
                totalNum = totalNum.add(item.getNum());
                //统计采购单中所有采购商品的总金额
                totalAmount = totalAmount.add(item.getAmount());
            }
            stockincomebill.setTotalamount(totalAmount);
            stockincomebill.setTotalnum(totalNum);
            stockincomebillService.save(stockincomebill);
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
    @ModelAttribute("updateStockincomebill")
    public Stockincomebill findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Stockincomebill stockincomebill = stockincomebillService.findOne(id);
            //解决N to N错误
            stockincomebill.setDepot(null);
            stockincomebill.setKeeper(null);
            stockincomebill.setSupplier(null);
            stockincomebill.getBillitems().clear();
            return stockincomebill;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param stockincomebill
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateStockincomebill")Stockincomebill stockincomebill){
        try {
            if(stockincomebill.getKeeper().getId() <= 0){
                throw new Exception("必须选择库管员！");
            }
            if(stockincomebill.getSupplier().getId() <= 0){
                throw new Exception("必须选择供应商！");
            }
            if(stockincomebill.getDepot().getId() <= 0){
                throw new Exception("必须选择仓库！");
            }
            stockincomebill.setInputuser(SessionUtils.getUser());  //设置录入人
            //入库单总金额和总数量
            BigDecimal totalNum = new BigDecimal(0);
            BigDecimal totalAmount = new BigDecimal(0);
            for (Stockincomebillitem item : stockincomebill.getBillitems()) {
                //每一个Purchasebillitem的bill属性不能为空（外键不能为空）组合关系就要求一方能够找到多方，多方也要能够找到一方
                item.setBill(stockincomebill);
                item.setAmount(item.getPrice().multiply(item.getNum()));    //计算采购单明细的小计金额
                //统计入库单中所有采购商品的总数量
                totalNum = totalNum.add(item.getNum());
                //统计入库单中所有采购商品的总金额
                totalAmount = totalAmount.add(item.getAmount());
            }
            stockincomebill.setTotalamount(totalAmount);
            stockincomebill.setTotalnum(totalNum);
            stockincomebillService.update(stockincomebill);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Stockincomebill findOne(Long id){
        return stockincomebillService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            stockincomebillService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    /**
     * 入库单审核
     * @param stockincomebill
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public ResultJson audit(Stockincomebill stockincomebill){
        try {
            //通过入库单id查询入库单【持久状态】
            Stockincomebill one = stockincomebillService.findOne(stockincomebill.getId());
            //审核时有可能增删改入库单明细数据 所以先清空再添加
            one.getBillitems().clear();
            if(one == null || one.getId() == null){
                throw new Exception("该入库单已不存在！");
            }
            if(one.getStatus() != 1){
                throw new Exception("该入库单不能被审核！");
            }
            Productstock productstock = null;
            //入库单总金额和总数量
            BigDecimal totalNum = new BigDecimal(0);
            BigDecimal totalAmount = new BigDecimal(0);
            for (Stockincomebillitem item : stockincomebill.getBillitems()) {
                //每一个Purchasebillitem的bill属性不能为空（外键不能为空）组合关系就要求一方能够找到多方，多方也要能够找到一方
                item.setBill(one);
                item.setAmount(item.getPrice().multiply(item.getNum()));    //计算采购单明细的小计金额
                //统计入库单中所有采购商品的总数量
                totalNum = totalNum.add(item.getNum());
                //统计入库单中所有采购商品的总金额
                totalAmount = totalAmount.add(item.getAmount());

                productstock = productstockService.findByProductAndDepot(item.getProduct().getId(), stockincomebill.getDepot().getId());
                if(productstock == null){
                    //库存中不存在当前要入库的商品时，直接新增一行
                    productstock = new Productstock();
                    productstock.setNum(item.getNum());
                    productstock.setPrice(item.getPrice());
                    productstock.setAmount(item.getNum().multiply(item.getPrice()));
                    productstock.setProduct(item.getProduct());
                    productstock.setDepot(stockincomebill.getDepot());
                    productstockService.save(productstock);
                }else{
                    //库存中已存在当前要入库的商品时，直接修改
                    productstock.setNum(productstock.getNum().add(item.getNum()));          //修改库存数量
                    productstock.setAmount(productstock.getAmount().add(item.getAmount())); //库存数据总金额
                    productstock.setPrice(productstock.getAmount()
                            .divide(productstock.getNum(),2, RoundingMode.HALF_UP));  //计算平均价
                    productstock.setIncomedate(new Date());
                    productstockService.update(productstock);
                }
                //当前表单提交过来的入库单明细是怎么样的，就添加到持久对象的入库单的明细集合中
                one.getBillitems().add(item);
            }

            //将表单提交过来的交易日期、库管员、供应商、仓库设置到持久对象身上
            one.setVdate(stockincomebill.getVdate());
            one.setKeeper(stockincomebill.getKeeper());
            one.setSupplier(stockincomebill.getSupplier());
            one.setDepot(stockincomebill.getDepot());
            one.setTotalamount(totalAmount);
            one.setTotalnum(totalNum);
            one.setStatus(2);
            one.setAuditor(SessionUtils.getUser());
            one.setAuditortime(new Date());
            stockincomebillService.update(one);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"审核失败：" + e.getMessage());
        }
        return new ResultJson(200,"审核成功！");
    }


}