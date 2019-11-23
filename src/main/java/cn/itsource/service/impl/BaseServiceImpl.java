package cn.itsource.service.impl;

import cn.itsource.query.BaseQuery;
import cn.itsource.repository.IBaseRepository;
import cn.itsource.service.IBaseService;
import cn.itsource.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 这里的父类千万不能写@Service注解，因为写了以后Spring扫描到这个类就必须要创建此类的对象，
 * 此时就必须要依赖注入一个IBaseRepository接口对象，而我们的dao层会写很多个IBaseRepository的子接口，那就无法确定
 * 到底要注入哪一个对象，Spring就自闭了！！就会抛出异常！！
 * @param <T>
 */
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
    public void delete(Long id) {
        baseRepository.delete(id);
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
        return baseRepository.findOne(id);
    }

    @Override
    public List<T> findAll(BaseQuery baseQuery) {
        return baseRepository.findByQuery(baseQuery);
    }

    @Override
    public MyPage<T> findByPage(BaseQuery baseQuery) {
        Page<T> page = baseRepository.findPageByQuery(baseQuery);
        return new MyPage<T>(baseQuery.getPageNo(),baseQuery.getPageSize(),page.getTotalElements(),page.getContent());
    }
}
