package cn.itsource.test;

import cn.itsource.domain.Purchasebillitem;
import cn.itsource.repository.IPurchasebillitemRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Test02 extends BaseTest {

    @Autowired
    private IPurchasebillitemRepository repository;


    @Test
    public void test() throws Exception{
        String jpql = "select item from Purchasebillitem item where item.bill.supplier.id=?1";
        List<Purchasebillitem> list = repository.findByJpql(jpql, 1L);
        list.forEach(item-> System.out.println(item));
    }

}
