package cn.itsource.ssj.shiro;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

public class ShiroFilterMapFactory {
    public Map<String, Filter> getShiroFiltersMap(){
        Map<String, Filter> map = new HashMap<>();
        map.put("myPerms", new AjaxPermissionsAuthorizationFilter());
        return map;
    }
}
