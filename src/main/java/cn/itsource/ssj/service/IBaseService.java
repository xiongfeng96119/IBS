package cn.itsource.ssj.service;

import cn.itsource.ssj.query.BaseQuery;
import cn.itsource.ssj.util.MyPage;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {
    void save(T t);
    void delete(String ids);
    void update(T t);
    T findOne(Long id);
    List<T> findAll();
    List<T> findByQuery(BaseQuery baseQuery);
    MyPage<T> findPageByQuery(BaseQuery baseQuery);
}
