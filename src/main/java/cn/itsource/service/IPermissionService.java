package cn.itsource.service;

import cn.itsource.domain.Permission;

import java.util.Set;

/**
 * (Permission)Service层接口
 *
 * @author 吴昌勇
 * @since 2019-11-11 14:06:13
 */
public interface IPermissionService extends IBaseService<Permission> {

    /**
     * 通过当前登录用户id查询它拥有的权限
     * @param id
     * @return
     */
    Set<String> findPermissionsById(Long id);
    
}