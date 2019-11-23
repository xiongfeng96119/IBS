package cn.itsource.shiro;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationFilterFactory {

    public Map<String, Filter> getFilters(){
        Map<String, Filter> map = new HashMap<>();
        map.put("ajaxperms", new AjaxPermissionsAuthorizationFilter());
        return map;
    }

}
