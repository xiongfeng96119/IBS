package cn.itsource.service.impl;

import cn.itsource.domain.Producttype;
import cn.itsource.repository.IProducttypeRepository;
import cn.itsource.service.IProducttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Producttype)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:27:18
 */
@Service
@Transactional(readOnly = true)
public class ProducttypeServiceImpl extends BaseServiceImpl<Producttype> implements IProducttypeService{
    @Autowired
    private IProducttypeRepository producttypeRepository;

    @Override
    public List<Producttype> findAll(Boolean loadParent) {
        String jpql = null;
        Producttype producttype = null;
        if(loadParent){
            jpql = "select pt from Producttype pt where pt.parent is null";
            producttype = new Producttype(-1L, "请选择父级类型");
        }else{
            jpql = "select pt from Producttype pt where pt.parent is not null";
            producttype = new Producttype(-1L, "请选择商品类型");
        }
        List<Producttype> list = producttypeRepository.findByJpql(jpql);
        list.add(0, producttype);
        return list;
    }
}