package cn.itsource.repository.impl;

import cn.itsource.query.BaseQuery;
import cn.itsource.repository.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T,ID>
        implements IBaseRepository<T,ID> {

    private EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public Page<T> findPageByQuery(BaseQuery baseQuery) {
        return this.findAll(baseQuery.getSpecification(), baseQuery.getPageable());
    }

    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        return this.findAll(baseQuery.getSpecification());
    }

    @Override
    public List<T> findByJpql(String jpql, Object... values) {
        Query query = entityManager.createQuery(jpql);
        if(values != null && values.length > 0){
            for(int i=0;i<values.length;i++){
                query.setParameter(i+1, values[i]);
            }
        }
        return query.getResultList();
    }
}
