package cn.iocoder.springboot.lab83.demo.order;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TransactionInterceptorBefore
 *
 * @author fw001
 * @date 2023/09/12 14:16
 **/
@Component
@Aspect
@Order(1)
public class TransactionInterceptorBefore {

    @Pointcut("execution(* cn.iocoder.springboot.lab83.demo.order.UserService.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object tsBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--------before start!!!");
        Object result = joinPoint.proceed();
        System.out.println("--------before end!!!");
        return result;
    }

}
