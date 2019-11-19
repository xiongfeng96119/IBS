package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.domain.Menu;
import cn.itsource.ssj.query.MenuVoQuery;
import cn.itsource.ssj.service.IMenuService;
import cn.itsource.ssj.service.IMenuVoService;
import cn.itsource.ssj.shiro.SessionUtils;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import cn.itsource.ssj.domain.MenuVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * (Menu)controller
 *
 * @author 熊峰
 * @since 2019-11-12 09:37:15
 */
@Controller
@RequestMapping("/menu")
public class MenuController{
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuVoService menuVoService;
    @RequestMapping("/index")
    public String index(){
        return "menu/menu";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<MenuVo> page(MenuVoQuery menuVoQuery){
        return menuVoService.findPageByQuery(menuVoQuery);
    }
    @ModelAttribute("findOneById")
    public Menu findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Menu menu = menuService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return menu;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Menu findOne(Long id){
        return menuService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Menu menu){
        try {
            if (menu.getParent().getId()==null){
                menu.setUrl(null);
                menu.setParent(null);
                menu.setIcon("icon-tree-parent");
            }else {
                Menu menu1 = menuService.findOne(menu.getParent().getId());
                menu.setParent(menu1);
                menu.setIcon("icon-tree-child");
            }
            menuService.save(menu);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Menu menu){
        try {
            menuService.update(menu);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"修改失败：" + e.getMessage());
        }
        return new Result(200, "修改成功！");
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(String ids){
        try {
            menuService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
    @ResponseBody
    @RequestMapping("/findMenusByEmployeeid")
    public List<Menu> findMenuByEmployeeId(Long id){
        Employee employee = SessionUtils.getUser();
        return menuService.findByEmployeeId(employee.getId());
    }
    @ResponseBody
    @RequestMapping("/findAllChildren")
    public List<Menu> findAllChildren(){
        return menuService.findAllChildren();
    }
    @ResponseBody
    @RequestMapping("/finAllParent")
    public List<Menu> finAllParent(){
        return menuService.findAllParent();
    }
}