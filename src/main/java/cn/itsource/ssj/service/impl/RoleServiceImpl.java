package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Role;
import cn.itsource.ssj.repository.IRoleRepository;
import cn.itsource.ssj.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Role)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-12 09:43:42
 */
 @Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;
}