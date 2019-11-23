package cn.itsource.service.impl;

import cn.itsource.domain.Role;
import cn.itsource.repository.IRoleRepository;
import cn.itsource.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Role)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-11 14:06:14
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;
}