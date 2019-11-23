package cn.itsource.shiro;

import cn.itsource.utils.ResultJson;
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
        Subject subject = this.getSubject(request, response);           //获取主体对象

        //获取请求头信息
        HttpServletRequest req = (HttpServletRequest)request;
        String xRequestedWith = req.getHeader("X-Requested-With");
        if(StringUtils.hasText(xRequestedWith)){
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            //异步请求
            if (subject.getPrincipal() == null) {
                ResultJson json = new ResultJson(501,"对不起，由于太长时间没有操作，请重新登录！");
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(json);
                response.getWriter().write(jsonStr);
            }else{
                String uri = req.getRequestURI();
                ResultJson json = new ResultJson(500,"对不起，你没有访问\""+uri+"\"资源的权限！");
                //手动将对象转化为json格式的字符串
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(json);
                response.getWriter().write(jsonStr);
            }
            response.getWriter().flush();
            response.getWriter().close();
        }else{
            //同步请求
            if (subject.getPrincipal() == null) {                           //判断主体对象是否为null，如果为null表示未登录
                this.saveRequestAndRedirectToLogin(request, response);      //访问资源时未登录就跳转到loginUrl
            } else {
                String unauthorizedUrl = this.getUnauthorizedUrl();         //获取配置文件中配置的unauthorizedUrl
                if (StringUtils.hasText(unauthorizedUrl)) {                 //判断unauthorizedUrl字符串是否有内容
                    WebUtils.issueRedirect(request, response, unauthorizedUrl); //使用重定向方式跳转到unauthorizedUrl
                } else {
                    WebUtils.toHttp(response).sendError(401);             //如果是unauthorizedUrl为null或者""，就报401错误
                }
            }
        }
        return false;
    }
}
