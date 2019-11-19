package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Permission;
import cn.itsource.ssj.repository.IPermissionRepository;
import cn.itsource.ssj.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * (Permission)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-12 09:44:40
 */
 @Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService{
    @Autowired
    private IPermissionRepository permissionRepository;
    /**
     * 通过当前登录用户id查询它拥有的权限
     * @param id
     * @return
     */
    @Override
    public Set<String> findPermissionsById(Long id) {
        String jpql = "select p from Employee e join e.roles r join r.permissions p where e.id=?1";
        List<Permission> list = permissionRepository.findByJpql(jpql, id);
        Set<String> permissions = new HashSet<>();
        list.forEach(permission -> permissions.add(permission.getSn()));
        return permissions;
    }

}