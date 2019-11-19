package cn.itsource.ssj.service;

import cn.itsource.ssj.domain.Permission;

import java.util.List;
import java.util.Set;

/**
 * (Permission)service层接口
 *
 * @author 熊峰
 * @since 2019-11-12 09:44:40
 */
public interface IPermissionService extends IBaseService<Permission>{
    /**
     * 通过当前登录用户id查询它拥有的权限
     * @param id
     * @return
     */
    Set<String> findPermissionsById(Long id);
}