package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Supplier;
import cn.itsource.ssj.repository.ISupplierRepository;
import cn.itsource.ssj.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Supplier)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:30
 */
 @Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements ISupplierService{
    @Autowired
    private ISupplierRepository supplierRepository;
}