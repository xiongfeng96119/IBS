package cn.itsource.service.impl;

import cn.itsource.domain.Systemdictionarytype;
import cn.itsource.repository.ISystemdictionarytypeRepository;
import cn.itsource.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Systemdictionarytype)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:21:10
 */
@Service
@Transactional(readOnly = true)
public class SystemdictionarytypeServiceImpl extends BaseServiceImpl<Systemdictionarytype> implements ISystemdictionarytypeService{
    @Autowired
    private ISystemdictionarytypeRepository systemdictionarytypeRepository;
}