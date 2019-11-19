package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.query.BaseQuery;
import cn.itsource.ssj.repository.IBaseRepository;
import cn.itsource.ssj.service.IBaseService;
import cn.itsource.ssj.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
@Transactional(readOnly = true)
public class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    private IBaseRepository<T,Long> baseRepository;
    @Override
    @Transactional
    public void save(T t) {
        baseRepository.save(t);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        for (String id : ids.split(",")) {
            baseRepository.delete(Long.valueOf(id));
        }
    }

    @Override
    @Transactional
    public void update(T t) {
        baseRepository.save(t);
    }

    @Override
    public T findOne(Long id) {
        return (T)baseRepository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        return baseRepository.findByQuery(baseQuery);
    }

    @Override
    public MyPage<T> findPageByQuery(BaseQuery baseQuery) {
        Page<T> page = baseRepository.findPageByQuery(baseQuery);
        return new MyPage<T>(baseQuery.getPageNo(),baseQuery.getPageSize(),page.getTotalElements(),page.getContent());
    }
}
