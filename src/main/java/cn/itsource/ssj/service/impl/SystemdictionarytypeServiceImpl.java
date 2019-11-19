package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Systemdictionarytype;
import cn.itsource.ssj.repository.ISystemdictionarytypeRepository;
import cn.itsource.ssj.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Systemdictionarytype)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:32
 */
 @Service
public class SystemdictionarytypeServiceImpl extends BaseServiceImpl<Systemdictionarytype> implements ISystemdictionarytypeService{
    @Autowired
    private ISystemdictionarytypeRepository systemdictionarytypeRepository;

}