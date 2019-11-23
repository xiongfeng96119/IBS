package cn.itsource.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.itsource.domain.Productstock;
import cn.itsource.repository.IProductstockRepository;
import cn.itsource.service.IProductstockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Productstock)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:56
 */
@Service
@Transactional(readOnly = true)
public class ProductstockServiceImpl extends BaseServiceImpl<Productstock> implements IProductstockService{
    @Autowired
    private IProductstockRepository productstockRepository;

    @Override
    public Productstock findByProductAndDepot(Long productid, Long depotid) {
        String jpql = "select o from Productstock o where o.product.id=?1 and o.depot.id=?2";
        List<Productstock> list = productstockRepository.findByJpql(jpql, productid, depotid);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}