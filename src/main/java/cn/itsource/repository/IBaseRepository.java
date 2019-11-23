package cn.itsource.repository;

import cn.itsource.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

    /**
     * 根据Query拿到分页对象(分页)
     * @param baseQuery
     * @return
     */
    Page<T> findPageByQuery(BaseQuery baseQuery);

    /**
     * 根据Query拿到对应的所有数据(不分页)
     * @param baseQuery
     * @return
     */
    List<T> findByQuery(BaseQuery baseQuery);

    /**
     * 根据jpql与对应的参数拿到数据
     * @param jpql
     * @param values
     * @return
     */
    List<T> findByJpql(String jpql,Object... values);


}
