package cn.iocoder.springboot.lab73.aopdemo.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Aspect1
 *
 * @author jaquez
 * @date 2023/07/03 11:32
 **/
//@1：这个类需要使用 @Aspect 进行标注
@Aspect
public class Aspect1 {

    //@2：定义了一个切入点，可以匹配 Service1 中所有方法
    @Pointcut("execution(* cn.iocoder.springboot.lab73.aopdemo.aspectj.Service1.*(..))")
    public void pointcut1() {
    }

    //@3：定义了一个前置通知，这个通知对刚刚上面我们定义的切入点中的所有方法有效
    @Before(value = "pointcut1()")
    public void before(JoinPoint joinPoint) {
        //输出连接点的信息
        System.out.println("前置通知，" + joinPoint);
    }

    //@4：定义了一个异常通知，这个通知对刚刚上面我们定义的切入点中的所有方法有效
    @AfterThrowing(value = "pointcut1()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        //发生异常之后输出异常信息
        System.out.println(joinPoint + ",发生异常：" + e.getMessage());
    }

    @Pointcut("within(C1+)") //@1，等同于 target.getClass().equals(within表达式中指定的类型)
    public void pc() {
    }

    @Before("pc()") //@2
    public void beforeAdvice(JoinPoint joinpoint) {
        System.out.println(joinpoint);
    }

    //@1：匹配 proxy 是 Service3 类型的所有方法，等同于 service3.getClass().isAssignableFrom(proxy.getClass());
    @Pointcut("this(Service3)")
    public void pc3() {
    }

    @Before("pc3()")
    public void beforeAdvice3(JoinPoint joinpoint) {
        System.out.println(joinpoint);
    }

    //@1：目标类型必须是 Service3 类型的，等同于 x.getClass().isAssignableFrom(target.getClass());
    @Pointcut("target(Service3)")
    public void pct() {
    }

    @Before("pct()")
    public void beforeAdviceT(JoinPoint joinpoint) {
        System.out.println(joinpoint);
    }

    //@1：匹配只有 1 个参数其类型是 String 类型的
    @Pointcut("args(String)")
    public void pcS() {
    }

    @Before("pcS()")
    public void beforeAdviceS(JoinPoint joinpoint) {
        System.out.println("请求参数：" + Arrays.stream(joinpoint.getArgs()).collect(Collectors.toList()));
    }

    /**
     * 定义目标方法的类上有 Ann9 注解，等同于被调用的目标方法 Method 对象.getDeclaringClass().getAnnotation(within 中指定的注解类型) != null
     */
    @Pointcut("@within(Ann9)")
    public void pcA() {
    }

    @Before("pcA()")
    public void beforeAdviceA(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }

    //匹配目标方法声明的类上有 @Anno10 注解
    @Pointcut("@within(Ann10)")
    public void pcA10() {
    }

    @Before("pcA10()")
    public void beforeAdviceA10(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }

    //@1：目标类上有 @Ann6 注解，等同于 target.class.getAnnotation(指定的注解类型) != null
    @Pointcut("@target(Ann6)")
    public void pcA6() {
    }

    @Before("pcA6()")
    public void beforeAdviceA6(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }

    /**
     * 匹配目标类上有 Ann7 注解
     */
    @Pointcut("@target(Ann7)")
    public void pcA7() {
    }

    @Before("pcA7()")
    public void beforeAdviceA7(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }

    @Pointcut("@annotation(Ann12)")
    public void pcA12() {
    }

    @Before("pcA12()")
    public void beforeAdviceA12(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }

}
