package cn.iocoder.springboot.lab73.aopdemo.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author jaquez
 * @date 2023/07/10 11:29
 **/

@Aspect
public class Aspect13 {

    //拦截 spring 容器中名称为 beanService2 的 bean
    @Pointcut("bean(beanService2)")
    public void pc() {
    }

    @Before("pc()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }
}
