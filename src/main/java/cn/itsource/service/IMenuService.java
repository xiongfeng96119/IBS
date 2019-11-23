package cn.itsource.service;

import cn.itsource.domain.Menu;
import cn.itsource.query.BaseQuery;
import cn.itsource.utils.MyPage;
import cn.itsource.vo.MenuVo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * (Menu)Service层接口
 *
 * @author 吴昌勇
 * @since 2019-11-08 16:27:35
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 通过当前登录用户id查询它拥有哪些菜单
     * @param id
     * @return
     */
    List<Menu> findMenusByEmployeeid(Long id);

    /**
     * 查询菜单，做combobox的选项
     * @param loadParent  为true表示查询父级菜单，false表示查询子菜单
     * @return
     */
    List<Menu> findAll(Boolean loadParent);


}