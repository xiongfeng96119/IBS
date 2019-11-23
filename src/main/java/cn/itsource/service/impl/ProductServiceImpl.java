package cn.itsource.service.impl;

import java.math.BigDecimal;
import cn.itsource.domain.Product;
import cn.itsource.repository.IProductRepository;
import cn.itsource.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Product)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:27:17
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService{
    @Autowired
    private IProductRepository productRepository;
}