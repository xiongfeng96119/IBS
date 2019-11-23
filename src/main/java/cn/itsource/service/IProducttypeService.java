package cn.itsource.service;

import cn.itsource.domain.Producttype;

import java.util.List;

/**
 * (Producttype)Service层接口
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:27:18
 */
public interface IProducttypeService extends IBaseService<Producttype> {
    /**
     * 查询商品类型，做combobox的选项
     * @param loadParent 为true表示查询父级类型，false表示查询子类型
     * @return
     */
    List<Producttype> findAll(Boolean loadParent);
}