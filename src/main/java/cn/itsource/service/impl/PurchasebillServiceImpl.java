package cn.itsource.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import cn.itsource.domain.Purchasebill;
import cn.itsource.repository.IPurchasebillRepository;
import cn.itsource.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Purchasebill)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:56
 */
@Service
@Transactional(readOnly = true)
public class PurchasebillServiceImpl extends BaseServiceImpl<Purchasebill> implements IPurchasebillService{
    @Autowired
    private IPurchasebillRepository purchasebillRepository;
}