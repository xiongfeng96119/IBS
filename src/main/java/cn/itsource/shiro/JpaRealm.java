package cn.itsource.shiro;

import cn.itsource.domain.Employee;
import cn.itsource.service.IEmployeeService;
import cn.itsource.service.IPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class JpaRealm extends AuthorizingRealm {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public String getName() {
        return "JpaRealm";
    }

    /**
     * 身份认证
     *  只要doGetAuthenticationInfo方法返回一个null，就表示用户名错误，登录时就会报出UnknownAccountException
     *  只要返回非空的AuthenticationInfo对象，Shiro就会自动去验证密码是否正确，密码不正确就报出IncorrectCredentialsException
     * @param authenticationToken
     * @return AuthenticationInfo就是专门用来验证密码是否正确
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //类型强转
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //获取用户名和密码（相当于从前端表单里面提交过来的用户名和密码）
        String username = token.getUsername();
        //通过用户名查询用户表
        Employee employee = employeeService.findByUsername(username);
        if(employee != null){
            //在这里加盐值需一个ByteSource对象，而Shiro提供了一个ByteSource对象给咱们
            ByteSource salt = ByteSource.Util.bytes("itsource.cn");
            /**
             * Object principal     主体对象（当前通过用户名查询到的员工对象）
             * Object credentials   凭证（数据库查询出来的密码）
             * ByteSource credentialsSalt   盐值
             * String realmName     当前自定义Realm取一个名字
             */
            return new SimpleAuthenticationInfo(employee, employee.getPassword(), salt, getName());
        }
        return null;
    }

//    /**
//     * 这里是模拟通过用户名查询一个用户对象出来，今后这里要换成真正的从数据库中查询
//     * @param username
//     * @return
//     */
//    private Employee findByUsername(String username) {
//        if("admin".equals(username)){
//            Employee employee = new Employee();
//            employee.setUsername("admin");
//            //密码是123 加密10次，盐值是itsource.cn
//            employee.setPassword("b01907eb3171b819ab54eb97ab0810a9");
//            employee.setId(1L);
//            return employee;
//        }
//        return null;
//    }

    /**
     * 授权：给当前登录用户授权【我让当前登录拥有哪些权限】
     * 返回的AuthorizationInfo对象就会自动完成权限认证（判断有没有某个权限），而我们只需要给它授权即可
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取Shiro的上下文中的当前登录用户对象
        //Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        //Employee employee = (Employee)SecurityUtils.getSubject().getPrincipal();
        //Employee employee = (Employee)SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        Employee employee = SessionUtils.getUser();
        //通过employee的id去查询数据库，获取他拥有哪些权限
        Set<String> permissions = permissionService.findPermissionsById(employee.getId());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //授权
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

//    /**
//     * 模拟通过员工id查询该员工拥有哪些权限，返回一个Set<String>
//     * @param id
//     * @return
//     */
//    private Set<String> findPermissionsById(Long id) {
//        Set<String> set = new HashSet<>();
//        set.add("employee:*");
//        return set;
//    }


}
