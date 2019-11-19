package cn.itsource.ssj.service;

import cn.itsource.ssj.domain.Systemdictionarydetail;

import java.util.List;

/**
 * (Systemdictionarydetail)service层接口
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:31
 */
public interface ISystemdictionarydetailService extends IBaseService<Systemdictionarydetail>{
    /**
     * 查询所有产品单位
     * @return
     */
    List<Systemdictionarydetail> findUnit();

    /**
     * 查询所有产品品牌
     * @return
     */
    List<Systemdictionarydetail> findBrand();
}