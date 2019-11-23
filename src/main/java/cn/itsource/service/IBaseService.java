package cn.itsource.service;

import cn.itsource.query.BaseQuery;
import cn.itsource.utils.MyPage;

import java.util.List;

public interface IBaseService<T> {

    void save(T t);
    void delete(Long id);
    void delete(String ids);
    void update(T t);
    T findOne(Long id);
    List<T> findAll(BaseQuery baseQuery);
    MyPage<T> findByPage(BaseQuery baseQuery);

}
