package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Purchasebillitem;
import cn.itsource.ssj.repository.IPurchasebillitemRepository;
import cn.itsource.ssj.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Purchasebillitem)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-18 10:22:59
 */
 @Service
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem> implements IPurchasebillitemService{
    @Autowired
    private IPurchasebillitemRepository purchasebillitemRepository;
}