package cn.itsource.ssj.service;

import cn.itsource.ssj.domain.Menu;
import cn.itsource.ssj.domain.Producttype;

import java.util.List;

/**
 * (Producttype)service层接口
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:29
 */
public interface IProducttypeService extends IBaseService<Producttype>{
    /**
     * 查询所有父级类型
     * @return
     */
    List<Producttype> findAllParent();
    /**
     * 查询所有子类型
     * @return
     */
    List<Producttype> findAllChildren();
}