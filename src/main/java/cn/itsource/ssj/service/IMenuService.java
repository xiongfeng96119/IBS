package cn.itsource.ssj.service;

import cn.itsource.ssj.domain.Menu;
import cn.itsource.ssj.service.IBaseService;

import java.util.List;

/**
 * (Menu)service层接口
 *
 * @author 熊峰
 * @since 2019-11-12 09:37:14
 */
public interface IMenuService extends IBaseService<Menu> {
    /**
     * 通过当前用户id查询菜单
     * @param id
     * @return
     */
    List<Menu> findByEmployeeId(Long id);

    /**
     * 查询所有子菜单
     * @return
     */
    List<Menu> findAllChildren();
    /**
     * 查询所有父级菜单
     * @return
     */
    List<Menu> findAllParent();

}