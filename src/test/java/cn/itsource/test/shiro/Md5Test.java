package cn.itsource.test.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class Md5Test {

    @Test
    public void test() throws Exception{
        String password = "123";
        //加密工具
        SimpleHash simpleHash = new SimpleHash("MD5",password,"itsource.cn",10);
        System.out.println(simpleHash);
    }

}
