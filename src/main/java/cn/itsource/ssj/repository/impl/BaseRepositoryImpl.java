package cn.itsource.ssj.repository.impl;

import cn.itsource.ssj.query.BaseQuery;
import cn.itsource.ssj.repository.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements IBaseRepository<T,ID> {
    private final EntityManager entityManager;
    //必需要实现父类的这个构造器
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public Page findPageByQuery(BaseQuery baseQuery) {
        //拿到高级查询条件
        Specification spec = baseQuery.createSpecification();
        //拿到排序的值
        Sort sort = baseQuery.getSort();
        //根据条件查询
        Pageable pageable = new PageRequest(baseQuery.getJpaPageNo(), baseQuery.getPageSize(),sort);
        Page<T> page = super.findAll(spec, pageable);
        return page;
    }

    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        //取到高级查询条件
        Specification spec = baseQuery.createSpecification();
        //取到排序的值
        Sort sort = baseQuery.getSort();
        return findAll(spec, sort);
    }

    @Override
    public List<T> findByJpql(String jpql, Object... values) {
        //创建Query对象
        Query query = entityManager.createQuery(jpql);
        //把值传到Query对象中去
        if (values!=null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.getResultList();
    }
}
