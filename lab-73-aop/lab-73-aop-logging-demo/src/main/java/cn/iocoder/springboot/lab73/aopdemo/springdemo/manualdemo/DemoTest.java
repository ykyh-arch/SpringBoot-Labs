package cn.iocoder.springboot.lab73.aopdemo.springdemo.manualdemo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DemoTest
 *
 * @author jaquez
 * @date 2023/06/16 17:49
 **/
public class DemoTest {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig1.class);
        //获取代理对象，代理对象 bean 的名称为注册 ProxyFactoryBean 的名称，即：service1Proxy
        Service1 bean = context.getBean("service1Proxy", Service1.class);
        System.out.println("----------------------");
        //调用代理的方法
        bean.m1();
        System.out.println("----------------------");
        //调用代理的方法
        bean.m2();
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        //获取代理对象，代理对象 bean 的名称为注册 ProxyFactoryBean 的名称，即：service1Proxy
        Service1 bean = context.getBean("service1Proxy", Service1.class);
        System.out.println("----------------------");
        //调用代理的方法
        bean.m1();
        System.out.println("----------------------");
        //调用代理的方法
        bean.m2();
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        //获取代理对象，代理对象 bean 的名称为注册 ProxyFactoryBean 的名称，即：service1Proxy
        Service1 bean = context.getBean("service1Proxy", Service1.class);
        System.out.println("----------------------");
        //调用代理的方法
        bean.m1();
        System.out.println("----------------------");
        //调用代理的方法
        bean.m2();
    }
}
