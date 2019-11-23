package cn.itsource.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.itsource.domain.Purchasebillitem;
import cn.itsource.query.PurchasebillitemQuery;
import cn.itsource.repository.IPurchasebillitemRepository;
import cn.itsource.service.IPurchasebillitemService;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import cn.itsource.vo.PurchaseBillItemVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Purchasebillitem)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:58
 */
@Controller
@RequestMapping("/purchasebillitem")
public class PurchasebillitemController {
    @Autowired
    private IPurchasebillitemService purchasebillitemService;

    @RequestMapping("/index")
    public String index(){
        return "purchasebillitem/purchasebillitem";
    }

    @ResponseBody
    @RequestMapping("/page")
    public List<PurchaseBillItemVo> page(PurchasebillitemQuery purchasebillitemQuery){
        return purchasebillitemService.findReportData(purchasebillitemQuery);
    }

    @ResponseBody
    @RequestMapping("/find3DPieData")
    public List<Map<String,Object>> find3DPieData(PurchasebillitemQuery purchasebillitemQuery){
        return purchasebillitemService.find3DPieData(purchasebillitemQuery);
    }


}