package cn.itsource.test.permission;

import cn.itsource.domain.Permission;
import cn.itsource.repository.IPermissionRepository;
import cn.itsource.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionTest extends BaseTest {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Test
    public void test() throws Exception{
        //假设这就是当前登录用户的id
        Long id = 2L;
        String jpql = "select p from Employee e join e.roles r join r.permissions p where e.id=?1";
        List<Permission> list = permissionRepository.findByJpql(jpql, id);
        list.forEach(permission -> System.out.println(permission));
    }

}
