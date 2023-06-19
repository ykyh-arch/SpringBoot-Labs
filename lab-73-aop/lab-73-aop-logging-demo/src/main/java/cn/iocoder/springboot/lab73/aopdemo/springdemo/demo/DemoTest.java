package cn.iocoder.springboot.lab73.aopdemo.springdemo.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * DemoTest
 * 参考自：https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648934876&idx=1&sn=7794b50e658e0ec3e0aff6cf5ed4aa2e&chksm=886211e2bf1598f4e0e636170a4b36a5a5edd8811c8b7c30d61135cb114b0ce506a6fa84df0b&token=690771459&lang=zh_CN#rd
 *
 * @author jaquez
 * @date 2023/06/16 17:49
 **/
public class DemoTest {

    @Test
    public void test1() {
        // 定义目标对象
        UserService target = new UserService();
        // 创建 pointcut，用来拦截 UserService 中的 work 方法
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                // 判断是否是 UserService 类型的
                return clazz -> UserService.class.isAssignableFrom(clazz);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        // 判断方法名称是否是 work
                        return "work".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        };
        // 创建通知，此处需要在方法之前执行操作，所以需要用到 MethodBeforeAdvice 类型的通知
        MethodBeforeAdvice advice = (method, args, target1) -> System.out.println("你好:" + args[0]);

        // 创建 Advisor，将 pointcut 和 advice 组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        //通过 spring 提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        // 为工厂指定目标对象
        proxyFactory.setTarget(target);
        //调用 addAdvisor 方法，为目标添加增强的功能，即添加 Advisor，可以为目标添加很多个 Advisor
        proxyFactory.addAdvisor(advisor);
        // 通过工厂提供的方法来生成代理对象
        UserService userServiceProxy = (UserService) proxyFactory.getProxy();

        // 调用代理的 work 方法
        userServiceProxy.work("白忙活");
    }

    @Test
    public void test2() {
        // 定义目标对象
        UserService target = new UserService();
        // 创建 pointcut，用来拦截 UserService 中的 work 方法
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                // 判断是否是 UserService 类型的
                return clazz -> UserService.class.isAssignableFrom(clazz);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        // 判断方法名称是否是 work
                        return "work".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        };
        //创建通知，需要拦截方法的执行，所以需要用到 MethodInterceptor 类型的通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("准备调用:" + invocation.getMethod());
                long starTime = System.nanoTime();
                Object result = invocation.proceed();
                long endTime = System.nanoTime();
                System.out.println(invocation.getMethod() + "，调用结束！");
                System.out.println("耗时(纳秒):" + (endTime - starTime));
                return result;
            }
        };

        // 创建 Advisor，将 pointcut 和 advice 组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        // 通过 spring 提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        // 为工厂指定目标对象
        proxyFactory.setTarget(target);
        // 调用 addAdvisor 方法，为目标添加增强的功能，即添加 Advisor，可以为目标添加很多个 Advisor
        proxyFactory.addAdvisor(advisor);
        // 通过工厂提供的方法来生成代理对象
        UserService userServiceProxy = (UserService) proxyFactory.getProxy();

        //调用代理的 work 方法
        userServiceProxy.work("路人");
    }

    @Test
    public void test3() {
        // 定义目标对象
        UserService target = new UserService();
        //创建 pointcut，用来拦截 UserService 中的 work 方法
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                // 判断是否是 UserService 类型的
                return clazz -> UserService.class.isAssignableFrom(clazz);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        // 判断方法名称是否是 work
                        return "work".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return true; // @1：注意这个地方要返回 true
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        // @2：isRuntime为true的时候，会执行这个方法
                        if (Objects.nonNull(args) && args.length == 1) {
                            String userName = (String) args[0];
                            return userName.contains("粉丝");
                        }
                        return false;
                    }
                };
            }
        };
        //创建通知，此处需要在方法之前执行操作，所以需要用到 MethodBeforeAdvice 类型的通知
        MethodBeforeAdvice advice = (method, args, target1) -> System.out.println("感谢您一路的支持!");

        //创建 Advisor，将 pointcut 和 advice 组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        //通过 spring 提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        //为工厂指定目标对象
        proxyFactory.setTarget(target);
        //调用 addAdvisor 方法，为目标添加增强的功能，即添加 Advisor，可以为目标添加很多个 Advisor
        proxyFactory.addAdvisor(advisor);
        //通过工厂提供的方法来生成代理对象
        UserService userServiceProxy = (UserService) proxyFactory.getProxy();

        //调用代理的 work 方法
        userServiceProxy.work("粉丝：A");
    }
}
