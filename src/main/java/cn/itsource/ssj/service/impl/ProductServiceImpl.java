package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Product;
import cn.itsource.ssj.domain.Producttype;
import cn.itsource.ssj.repository.IProductRepository;
import cn.itsource.ssj.repository.IProducttypeRepository;
import cn.itsource.ssj.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (Product)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:27
 */
 @Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService{
    @Autowired
    private IProductRepository productRepository;

}