package cn.itsource.service;

import cn.itsource.domain.Systemdictionarydetail;

import java.util.List;

/**
 * (Systemdictionarydetail)Service层接口
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:21:09
 */
public interface ISystemdictionarydetailService extends IBaseService<Systemdictionarydetail> {

    /**
     * 通过数据字典明细所属的数据字典类型的sn查询明细
     * @param sn
     * @return
     */
    List<Systemdictionarydetail> findDictionaryDetails(String sn);

}