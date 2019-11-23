package cn.itsource.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itsource.domain.Purchasebillitem;
import cn.itsource.query.PurchasebillitemQuery;
import cn.itsource.repository.IPurchasebillitemRepository;
import cn.itsource.service.IPurchasebillitemService;
import cn.itsource.vo.PurchaseBillItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.OSEnvironment;

/**
 * (Purchasebillitem)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:58
 */
@Service
@Transactional(readOnly = true)
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem> implements IPurchasebillitemService{
    @Autowired
    private IPurchasebillitemRepository purchasebillitemRepository;

    @Override
    public List<PurchaseBillItemVo> findReportData(PurchasebillitemQuery purchasebillitemQuery) {
        String jpql = "select o from Purchasebillitem o" + purchasebillitemQuery.getJpql();
        //查询到所有的采购单明细
        List<Purchasebillitem> list = purchasebillitemRepository.findByJpql(jpql, purchasebillitemQuery.getParams().toArray());

        List<PurchaseBillItemVo> voList = new ArrayList<>();
        //循环遍历list，将每一个Purchasebillitem封装成一个PurchaseBillItemVo，保存到voList集合中
        for (Purchasebillitem item : list) {
            voList.add(new PurchaseBillItemVo(item, purchasebillitemQuery.getGroupField()));
        }
        return voList;
    }

    /**
     * 查询3D饼状图的数据
     * @param purchasebillitemQuery
     * @return
     */
    @Override
    public List<Map<String, Object>> find3DPieData(PurchasebillitemQuery purchasebillitemQuery) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String jpql = "select " + purchasebillitemQuery.getGroupField() + ",sum(o.amount) from Purchasebillitem o " + purchasebillitemQuery.getJpql() + " group by " + purchasebillitemQuery.getGroupField();
        List list = purchasebillitemRepository.findByJpql(jpql, purchasebillitemQuery.getParams().toArray());
        Object[] os = null;
        Map<String, Object> map = null;
        int i = 0;
        for (Object o : list) {
            os = (Object[])o;
            map = new HashMap<>();
            map.put("name", os[0]);
            map.put("y", os[1]);
            map.put("sliced", true);
            if(i++ == 0){
                map.put("selected", true);
            }
            mapList.add(map);
        }
        return mapList;
    }
}