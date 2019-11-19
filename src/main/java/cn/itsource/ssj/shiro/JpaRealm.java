package cn.itsource.ssj.shiro;

import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.service.IEmployeeService;
import cn.itsource.ssj.service.IPermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;

public class JpaRealm extends AuthorizingRealm {
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    IPermissionService permissionService;
    //获取Realm的名字
    @Override
    public String getName() {
        return "JpaRealm";
    }

    /**
     * 授权:让当前登录用户拥有哪些权限
     * 返回的AuthorizationInfo对象会自动完成授权认证，我们只需要授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户名
        Employee loginUser = SessionUtils.getUser();
        //通过employee的id去查询数据库，获取他拥有的权限
        Set<String> permissions = permissionService.findPermissionsById(loginUser.getId());
        System.out.println(permissions);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //授权
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }


    /**
     * 登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //类型强转
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //前台用户登录时传过来的用户名
        String username = token.getUsername();
        Employee employee = employeeService.findByUsername(username);
        if (employee != null){
            /**
             * Object principal             通过当前用户查询到的对象
             * Object credentials           对象的密码
             * ByteSource credensialsSalt   盐值
             * String realmName             当前自定义realm取的名字
             */
            ByteSource salt = ByteSource.Util.bytes("cn.itsource");
            return new SimpleAuthenticationInfo(employee, employee.getPassword(),salt,getName());
        }
        return null;
    }
}
