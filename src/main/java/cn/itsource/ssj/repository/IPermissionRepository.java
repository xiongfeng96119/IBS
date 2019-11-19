package cn.itsource.ssj.repository;

import cn.itsource.ssj.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * (Permission)dao层接口
 *
 * @author 熊峰
 * @since 2019-11-12 09:44:40
 */
public interface IPermissionRepository extends IBaseRepository<Permission,Long>{
}