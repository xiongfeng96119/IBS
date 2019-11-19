package cn.itsource.ssj.repository;

import cn.itsource.ssj.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义一个Repository,它是JpaRepository的功能基础上继承增强
 * 在上面添加@NoRepositoryBean标注，这样SpringDataJpa在启动时就不会去实例化BaseRepository这个接口
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface IBaseRepository<T,ID extends Serializable>
        extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {
    //根据Query拿到分页对象(分页)
    Page findPageByQuery(BaseQuery baseQuery);

    //根据Query拿到对应的所有数据(不分页)
    List<T> findByQuery(BaseQuery baseQuery);

    //根据jpql与对应的参数拿到数据
    List findByJpql(String jpql,Object... values);
}
