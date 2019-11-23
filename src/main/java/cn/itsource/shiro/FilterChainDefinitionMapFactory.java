package cn.itsource.shiro;

import cn.itsource.domain.Permission;
import cn.itsource.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FilterChainDefinitionMapFactory {

    @Autowired
    private IPermissionRepository permissionRepository;

    /**
     * 写成java代码就可以添加查询数据库的代码，只需要依赖注入Dao层对象就可以了
     * @return
     */
    public Map<String, String> getFilterChainDefinitionMap(){
        //要注意里面的键值对的顺序 authc必须放在最后，其他的可以随便放
        Map<String, String> map = new LinkedHashMap<>();
        //不需要登录即可访问的资源
        map.put("/favicon.ico", "anon");
        map.put("/**/*.png", "anon");
        map.put("/**/*.jpg", "anon");
        map.put("/**/*.css", "anon");
        map.put("/login/**", "anon");
        map.put("/easyui/**", "anon");
        map.put("/**/*.js", "anon");
        map.put("/page/**", "anon"); //登录表单提交地址必须放开，不然永远登录不了
        //需要授权才能访问的资源【这一节代码换成从数据库查询得来】
        List<Permission> list = permissionRepository.findAll();
        list.forEach(permission -> map.put(permission.getUrl(), "ajaxperms["+permission.getSn()+"]"));
        //除了以上资源之外的其他所有资源都是必须登录才能访问的:
        map.put("/**", "authc");
        return map;
    }

}
