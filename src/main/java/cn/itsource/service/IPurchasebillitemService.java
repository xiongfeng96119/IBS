package cn.itsource.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.itsource.domain.Purchasebillitem;
import cn.itsource.query.PurchasebillitemQuery;
import cn.itsource.utils.MyPage;
import cn.itsource.vo.PurchaseBillItemVo;

/**
 * (Purchasebillitem)Service层接口
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:58
 */
public interface IPurchasebillitemService extends IBaseService<Purchasebillitem> {
    /**
     * 查询页面报表数据
     * @param purchasebillitemQuery
     * @return
     */
    List<PurchaseBillItemVo> findReportData(PurchasebillitemQuery purchasebillitemQuery);

    /**
     * 查询3D饼状图的数据
     * @param purchasebillitemQuery
     * @return
     */
    List<Map<String,Object>> find3DPieData(PurchasebillitemQuery purchasebillitemQuery);
}