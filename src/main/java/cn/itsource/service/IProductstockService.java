package cn.itsource.service;

import java.math.BigDecimal;
import java.util.Date;
import cn.itsource.domain.Productstock;

/**
 * (库存)Service层接口
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:56
 */
public interface IProductstockService extends IBaseService<Productstock> {

    /**
     * 通过商品和仓库查询库存数据
     * @param productid
     * @param depotid
     * @return
     */
    Productstock findByProductAndDepot(Long productid, Long depotid);

    
}