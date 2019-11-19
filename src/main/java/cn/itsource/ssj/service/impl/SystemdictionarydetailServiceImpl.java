package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Systemdictionarydetail;
import cn.itsource.ssj.domain.Systemdictionarytype;
import cn.itsource.ssj.repository.ISystemdictionarydetailRepository;
import cn.itsource.ssj.service.ISystemdictionarydetailService;
import cn.itsource.ssj.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Systemdictionarydetail)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:31
 */
 @Service
public class SystemdictionarydetailServiceImpl extends BaseServiceImpl<Systemdictionarydetail> implements ISystemdictionarydetailService{
    @Autowired
    private ISystemdictionarydetailRepository systemdictionarydetailRepository;

    /**
     * 查询所有产品单位
     * @return
     */
    @Override
    public List<Systemdictionarydetail> findUnit() {
        List<Systemdictionarydetail> list = systemdictionarydetailRepository.findByJpql("select s from Systemdictionarydetail s join s.types e where e.id=?1", 2L);
        return list;
    }
    /**
     * 查询所有产品品牌
     * @return
     */
    @Override
    public List<Systemdictionarydetail> findBrand() {
        List<Systemdictionarydetail> list = systemdictionarydetailRepository.findByJpql("select s from Systemdictionarydetail s join s.types e where e.id=?1", 1L);
        return list;
    }
}