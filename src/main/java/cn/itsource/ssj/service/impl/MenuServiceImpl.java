package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Menu;
import cn.itsource.ssj.repository.IMenuRepository;
import cn.itsource.ssj.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (Menu)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-12 09:37:15
 */
 @Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
    @Autowired
    private IMenuRepository menuRepository;

    @Override
    public List<Menu> findByEmployeeId(Long id) {
        //最顶级菜单的List集合
        List<Menu> firstLevel = new ArrayList<>();
        String jpql = "select m from Employee e join e.roles r join r.permissions p join p.menu m where e.id=?1";
        List<Menu> list = menuRepository.findByJpql(jpql, id);
        list.forEach(menu -> {
            //获取父级菜单
            Menu parent = menu.getParent();
            if(!firstLevel.contains(parent)){
                firstLevel.add(parent);
            }
            //将当前子菜单添加到父级菜单的子菜单集合中
            parent.getChildren().add(menu);
        });
        return firstLevel;
    }
    /**
     * 查询所有子菜单
     * @param
     * @return
     */
    @Override
    public List<Menu> findAllChildren() {
        List<Menu> list = menuRepository.findByJpql("select m from Menu m where m.parent is not null");
        return list;
    }
    /**
     * 查询所有父级菜单
     * @return
     */
    @Override
    public List<Menu> findAllParent() {
        List<Menu> list = menuRepository.findByJpql("select m from Menu m where m.parent is null");
        return list;
    }
}