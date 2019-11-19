package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Purchasebill;
import cn.itsource.ssj.repository.IPurchasebillRepository;
import cn.itsource.ssj.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Purchasebill)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-18 10:22:57
 */
 @Service
public class PurchasebillServiceImpl extends BaseServiceImpl<Purchasebill> implements IPurchasebillService{
    @Autowired
    private IPurchasebillRepository purchasebillRepository;
}