package cn.itsource.test.service;

import cn.itsource.domain.Dept;
import cn.itsource.query.DeptQuery;
import cn.itsource.service.IDeptService;
import cn.itsource.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeptServiceImplTest extends BaseTest {

    @Autowired
    private IDeptService deptService;


    @Test
    public void test() throws Exception{
        System.out.println(deptService);
        List<Dept> list = deptService.findAll(new DeptQuery());
        list.forEach(dept -> System.out.println(dept));
    }
}
