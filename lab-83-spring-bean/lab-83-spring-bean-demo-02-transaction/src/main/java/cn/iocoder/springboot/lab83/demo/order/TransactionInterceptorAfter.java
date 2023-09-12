package cn.iocoder.springboot.lab83.demo.order;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TransactionInterceptorAfter
 *
 * @author fw001
 * @date 2023/09/12 14:19
 **/
@Component
@Aspect
@Order(3)
public class TransactionInterceptorAfter {

    @Pointcut("execution(* cn.iocoder.springboot.lab83.demo.order.UserService.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object tsAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--------after start!!!");
        Object result = joinPoint.proceed();
        System.out.println("--------after end!!!");
        return result;
    }

}
