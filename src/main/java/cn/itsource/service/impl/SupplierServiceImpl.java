package cn.itsource.service.impl;

import cn.itsource.domain.Supplier;
import cn.itsource.repository.ISupplierRepository;
import cn.itsource.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Supplier)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:25:01
 */
@Service
@Transactional(readOnly = true)
public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements ISupplierService{
    @Autowired
    private ISupplierRepository supplierRepository;
}