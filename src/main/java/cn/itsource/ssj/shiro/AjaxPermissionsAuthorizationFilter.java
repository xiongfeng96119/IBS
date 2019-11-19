package cn.itsource.ssj.shiro;

import cn.itsource.ssj.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class AjaxPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = this.getSubject(request, response);
        //获取请求头对象
        HttpServletRequest req = (HttpServletRequest) request;
        String xRequestWith = req.getHeader("X-Requested-With");
        //异步请求
        if (StringUtils.hasText(xRequestWith)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            //用户没有登录
            if (subject.getPrincipal() == null) {
                Result result = new Result(501, "对不起，请重新登录！");
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(result);
                response.getWriter().write(jsonStr);
            } else {
                String uri = req.getRequestURI();
                Result result = new Result(500,"对不起，你没有访问\""+uri+"\"资源的权限！");
                //手动将对象转化为json格式的字符串
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(result);
                response.getWriter().write(jsonStr);
            }
            response.getWriter().flush();
            response.getWriter().close();
            //同步请求
        }else {
            //用户没有登录
            if (subject.getPrincipal() == null) {
                //跳转到登录页面
                this.saveRequestAndRedirectToLogin(request, response);
            } else {
                //获取配置文件中配置的unauthorizedUrl
                String unauthorizedUrl = this.getUnauthorizedUrl();
                //如果unauthorizedUrl有内容
                if (StringUtils.hasText(unauthorizedUrl)) {
                    //使用重定向方式跳转到unauthorizedUrl
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    //如果unauthorizedUrl为空，报401
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }
        return false;
    }
}
