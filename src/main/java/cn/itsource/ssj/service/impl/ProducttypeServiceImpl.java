package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Producttype;
import cn.itsource.ssj.repository.IProducttypeRepository;
import cn.itsource.ssj.service.IProducttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Producttype)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:29
 */
 @Service
public class ProducttypeServiceImpl extends BaseServiceImpl<Producttype> implements IProducttypeService{
    @Autowired
    private IProducttypeRepository producttypeRepository;
    /**
     * 查询所有父级类型
     * @return
     */
    @Override
    public List<Producttype> findAllParent() {
        List<Producttype> list = producttypeRepository.findByJpql("select p from Producttype p where p.parent is null");
        return list;
    }
    /**
     * 查询所有子类型
     * @return
     */
    @Override
    public List<Producttype> findAllChildren() {
        List<Producttype> list = producttypeRepository.findByJpql("select p from Producttype p where p.parent is not null");
        return list;    }
}