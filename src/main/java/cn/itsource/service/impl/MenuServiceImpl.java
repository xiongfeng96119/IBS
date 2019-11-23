package cn.itsource.service.impl;

import cn.itsource.domain.Menu;
import cn.itsource.query.BaseQuery;
import cn.itsource.repository.IMenuRepository;
import cn.itsource.service.IMenuService;
import cn.itsource.utils.MyPage;
import cn.itsource.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (Menu)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-08 16:27:35
 */
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService{
    @Autowired
    private IMenuRepository menuRepository;

    /**
     * 通过当前登录用户id查询它拥有哪些菜单
     *  查询到的一定是最低级菜单，而这个方法应该返回的顶级菜单的List集合
     * @param id
     * @return
     */
    @Override
    public List<Menu> findMenusByEmployeeid(Long id) {
        //最顶级菜单的List集合
        List<Menu> firstLevel = new ArrayList<>();

        String jpql = "select m from Employee e join e.roles r join r.permissions p join p.menu m where e.id=?1";
        List<Menu> list = menuRepository.findByJpql(jpql, id);
        list.forEach(menu -> {
            Menu parent = menu.getParent();     //得到当前最低级菜单的父级菜单
            if(!firstLevel.contains(parent)){
                //parent.getChildren().clear();
                firstLevel.add(parent);
            }
            //将当前子菜单添加到当前子菜单的父级菜单的子菜单集合中
            parent.getChildren().add(menu);
        });
        return firstLevel;
    }

    /**
     * 查询菜单，做combobox的选项
     * @param loadParent  为true表示查询父级菜单，false表示查询子菜单
     * @return
     */
    public List<Menu> findAll(Boolean loadParent){
        String jpql = null;
        Menu defaults = null;
        if(loadParent){
            defaults = new Menu(-1L, "请选择父级菜单");
            jpql = "select m from Menu m where m.parent is null";
        }else{
            defaults = new Menu(-1L, "请选择菜单");
            jpql = "select m from Menu m where m.parent is not null";
        }
        List<Menu> list = menuRepository.findByJpql(jpql);
        list.add(0, defaults);
        return list;
    }

}