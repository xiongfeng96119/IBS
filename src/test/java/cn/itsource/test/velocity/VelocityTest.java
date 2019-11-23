package cn.itsource.test.velocity;

import cn.itsource.domain.Department;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VelocityTest {

    @Test
    public void test() throws Exception{
        //Velocity上下文【内部就是使用键值对存储数据】
        VelocityContext context = new VelocityContext();
        //上下文中添加数据【类似于之前的请求、Session作用域中添加数据】
        //context.put("name", "张学友");
        //通过模本文件路径得到一个模板对象
        Template template = Velocity.getTemplate("hello.vm", "utf-8");
        //创建了一个输出流
        StringWriter writer = new StringWriter();
        //将上下文的数据与输出流合并
        template.merge(context, writer);

        System.out.println(writer);
        writer.close();
    }

    /**
     * 传入对象
     * @throws Exception
     */
    @Test
    public void test02() throws Exception{
        //Velocity上下文【内部就是使用键值对存储数据】
        VelocityContext context = new VelocityContext();
        //上下文中添加数据【类似于之前的请求、Session作用域中添加数据】
        Department department = new Department(1L, "测试部");
        context.put("department", department);
        //通过模本文件路径得到一个模板对象
        Template template = Velocity.getTemplate("hello.vm", "utf-8");
        //创建了一个输出流
        StringWriter writer = new StringWriter();
        //将上下文的数据与输出流合并
        template.merge(context, writer);

        System.out.println(writer);
        writer.close();
    }

    /**
     * 传入List集合
     * @throws Exception
     */
    @Test
    public void test03() throws Exception{
        //Velocity上下文【内部就是使用键值对存储数据】
        VelocityContext context = new VelocityContext();
        //上下文中添加数据【类似于之前的请求、Session作用域中添加数据】
        Department department01 = new Department(1L, "测试部");
        Department department02 = new Department(2L, "市场部");
        Department department03 = new Department(3L, "研发部");

        List<Department> list = new ArrayList<>();
        list.add(department01);
        list.add(department02);
        list.add(department03);

        context.put("list", list);
        //通过模本文件路径得到一个模板对象
        Template template = Velocity.getTemplate("hello.vm", "utf-8");
        //创建了一个输出流
        StringWriter writer = new StringWriter();
        //将上下文的数据与输出流合并
        template.merge(context, writer);

        System.out.println(writer);
        writer.close();
    }

    /**
     * 传入Map集合
     * @throws Exception
     */
    @Test
    public void test04() throws Exception{
        //Velocity上下文【内部就是使用键值对存储数据】
        VelocityContext context = new VelocityContext();
        //上下文中添加数据【类似于之前的请求、Session作用域中添加数据】
        Department department = new Department(1L, "测试部");

        Map<String,Object> map = new HashMap<>();
        map.put("department", department);
        map.put("name", "张无忌");
        map.put("age", 22);

        context.put("map", map);
        //通过模本文件路径得到一个模板对象
        Template template = Velocity.getTemplate("hello.vm", "utf-8");
        //创建了一个输出流
        StringWriter writer = new StringWriter();
        //将上下文的数据与输出流合并
        template.merge(context, writer);

        System.out.println(writer);
        writer.close();
    }

}
