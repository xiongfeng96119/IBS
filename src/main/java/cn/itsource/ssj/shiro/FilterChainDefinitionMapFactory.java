package cn.itsource.ssj.shiro;

import cn.itsource.ssj.domain.Permission;
import cn.itsource.ssj.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FilterChainDefinitionMapFactory {
    @Autowired
    private IPermissionService permissionService;
    public Map<String,String> getFilterChainDefinitionMap(){
        //authc必须放在最后
        Map<String,String> map = new LinkedHashMap<>();
        //不需登录即可访问的资源
        map.put("/favicon.ico", "anon");
        map.put("*.css", "anon");
        map.put("/css/**", "anon");
        map.put("/img/**", "anon");
        map.put("*.js", "anon");
        map.put("/js/**", "anon");
        map.put("/easyui/**","anon");
        map.put("/page/*", "anon");
        //需要授权才能访问的资源
        //拿到所有权限
        List<Permission> permissions = permissionService.findAll();
        //遍历权限放入自定义拦截器中
        permissions.forEach(permission -> map.put(permission.getUrl(),"myPerms["+permission.getSn()+"]"));
        System.out.println(map);
        //除以上资源外其他所有资源都需要登录后才能访问
        map.put("/**", "authc");
        return map;
    }
}
