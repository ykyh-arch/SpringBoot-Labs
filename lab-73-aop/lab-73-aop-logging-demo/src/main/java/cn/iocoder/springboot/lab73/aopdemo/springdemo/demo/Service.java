package cn.iocoder.springboot.lab73.aopdemo.springdemo.demo;

import org.springframework.aop.framework.AopContext;

/**
 * Service
 *
 * @author jaquez
 * @date 2023/06/28 14:42
 **/
public class Service implements IService{
    @Override
    public void say(String name) {
        System.out.println("hello：" + name);
    }

    public void m1() {
        System.out.println("m1");
        this.m2();
        //((Service) AopContext.currentProxy()).m2();
    }

    public void m2() {
        System.out.println("m2");
    }
}
