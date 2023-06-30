package cn.iocoder.springboot.lab73.aopdemo.springdemo.manualdemo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * MainConfig
 *
 * @author jaquez
 * @date 2023/06/30 14:51
 **/
@Configuration
public class MainConfig1 {

    // 注册目标对象
    @Bean
    public Service1 service1() {
        return new Service1();
    }

    // 注册一个前置通知
    @Bean
    public MethodBeforeAdvice beforeAdvice() {
        MethodBeforeAdvice advice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
                System.out.println("准备调用：" + method);
            }
        };
        return advice;
    }

    // 注册一个后置通知
    @Bean
    public MethodInterceptor costTimeInterceptor() {
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                long starTime = System.nanoTime();
                Object result = invocation.proceed();
                long endTime = System.nanoTime();
                System.out.println(invocation.getMethod() + ",耗时(纳秒)：" + (endTime - starTime));
                return result;
            }
        };
        return methodInterceptor;
    }

    // 注册 ProxyFactoryBean
    @Bean
    public ProxyFactoryBean service1Proxy() {
        //1.创建 ProxyFactoryBean
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //2.设置目标对象的 bean 名称
        proxyFactoryBean.setTargetName("service1");
        //3.设置拦截器的 bean 名称列表，此处2个（advice1 和 advice2)
        proxyFactoryBean.setInterceptorNames("beforeAdvice", "costTimeInterceptor");
        return proxyFactoryBean;
    }
}
