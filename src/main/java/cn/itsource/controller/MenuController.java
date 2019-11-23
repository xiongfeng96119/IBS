package cn.itsource.controller;

import cn.itsource.domain.Employee;
import cn.itsource.domain.Menu;
import cn.itsource.query.MenuQuery;
import cn.itsource.query.MenuVoQuery;
import cn.itsource.service.IMenuService;
import cn.itsource.service.IMenuVoService;
import cn.itsource.shiro.SessionUtils;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import cn.itsource.vo.MenuVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * (Menu)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-08 16:27:35
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuVoService menuVoService;
    
    @RequestMapping("/index")
    public String index(){
        return "menu/menu";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<MenuVo> page(MenuVoQuery menuVoQuery){
        return menuVoService.findByPage(menuVoQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Menu menu){
        try {
            if(menu.getParent() != null && menu.getParent().getId() != null && menu.getParent().getId() > 0){
                menu.setIcon("icon-tree-child");
            }else{
                menu.setIcon("icon-tree-parent");
                menu.setParent(null);
            }
            menuService.save(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"新增失败：" + e.getMessage());
        }
        return new ResultJson(200,"新增成功！");
    }

    /**
     * @ModelAttribute注解表示当前类的其他所有方法执行之前先执行此方法
     * @param id
     * @return
     */
    @ModelAttribute("updateMenu")
    public Menu findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Menu menu = menuService.findOne(id);
            //解决N to N错误
            menu.setParent(null);
            return menu;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateMenu")Menu menu){
        try {
            if(menu.getParent() != null && menu.getParent().getId() != null && menu.getParent().getId() > 0){
                menu.setIcon("icon-tree-child");
            }else{
                menu.setIcon("icon-tree-parent");
                menu.setParent(null);
            }
            menuService.update(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public MenuVo findOne(Long id){
        return menuVoService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            menuService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    @ResponseBody
    @RequestMapping("/findMenusByEmployeeid")
    public List<Menu> findMenusByEmployeeid(){
        Employee employee = SessionUtils.getUser();
        return menuService.findMenusByEmployeeid(employee.getId());
    }

    @ResponseBody
    @RequestMapping("/findAll")
    public List<Menu> findAll(Boolean loadParent){
        return menuService.findAll(loadParent);
    }

}