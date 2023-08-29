package cn.iocoder.springboot.lab73.aopdemo.aspectj;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ClassUtils;

/**
 * AspectTest
 * 参考自：https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648935037&idx=2&sn=cf813ac4cdfa3a0a0d6b5ed770255779&chksm=88621243bf159b554be2fe75eda7f5631ca29eed54edbfb97b08244625e03957429f2414d1e3&token=883563940&lang=zh_CN#rd
 *
 * @author jaquez
 * @date 2023/07/03 11:40
 **/
public class AspectTest {

    @Test
    public void test1() {
        try {
            //对应目标对象
            Service1 target = new Service1();
            //创建 AspectJProxyFactory 对象
            AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
            //设置被代理的目标对象
            proxyFactory.setTarget(target);
            //设置标注了 @Aspect 注解的类，解析成 Advisor 列表
            proxyFactory.addAspect(Aspect1.class);
            //生成代理对象
            Service1 proxy = proxyFactory.getProxy();
            //使用代理对象
            proxy.m1();
            proxy.m2();
        } catch (Exception e) {
        }
    }

    @Test
    public void test2(){
        C2 target = new C2();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect1.class);

        C2 proxy = proxyFactory.getProxy();
        proxy.m1();
        proxy.m2();
        proxy.m3();
    }

    @Test
    public void test3() {
        Service3 target = new Service3();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        //获取目标对象上的接口列表
        Class<?>[] allInterfaces = ClassUtils.getAllInterfaces(target);
        //设置需要代理的接口
        proxyFactory.setInterfaces(allInterfaces);
        proxyFactory.addAspect(Aspect1.class);
        proxyFactory.setProxyTargetClass(true);
        //获取代理对象
        Object proxy = proxyFactory.getProxy();
        //调用代理对象的方法
        ((I1) proxy).m1();

        System.out.println("proxy是否是jdk动态代理对象：" + AopUtils.isJdkDynamicProxy(proxy));
        System.out.println("proxy是否是cglib代理对象：" + AopUtils.isCglibProxy(proxy));
        //判断代理对象是否是 Service3 类型的
        System.out.println(Service3.class.isAssignableFrom(proxy.getClass()));
    }

    @Test
    public void test4() {
        Service3 target = new Service3();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect1.class);
        //获取代理对象
        Object proxy = proxyFactory.getProxy();
        //调用代理对象的方法
        ((I1) proxy).m1();
        //判断 target 对象是否是 Service3 类型的
        System.out.println(Service3.class.isAssignableFrom(target.getClass()));
    }

    @Test
    public void test5() {
        Service5 target = new Service5();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect1.class);
        Service5 proxy = proxyFactory.getProxy();
        proxy.m1("路人");
        proxy.m1(100);
    }

    @Test
    public void test9() {
        S9 target = new S9();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect1.class);
        S9 proxy = proxyFactory.getProxy();
        proxy.m1();
    }

    @Test
    public void test10() {
        S10 target = new S10();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect1.class);
        S10 proxy = proxyFactory.getProxy();
        proxy.m1();
        proxy.m2();
        proxy.m3();
    }

    @Test
    public void test6() {
        S6 target = new S6();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect1.class);
        S6 proxy = proxyFactory.getProxy();
        proxy.m1();
        System.out.println("目标类上是否有 @Ann6 注解：" + (target.getClass().getAnnotation(Ann6.class) != null));
    }

    @Test
    public void test7() {
        S7 target = new S7();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect1.class);
        S7 proxy = proxyFactory.getProxy();
        proxy.m1();
        System.out.println("目标类上是否有 @Ann7 注解：" + (target.getClass().getAnnotation(Ann7.class) != null));
    }

    @Test
    public void test12() {
        S12 target = new S12();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAspect(Aspect1.class);
        S12 proxy = proxyFactory.getProxy();
        proxy.m1();
        proxy.m2();
        proxy.m3();
        proxy.m4();
    }

    @Test
    public void test13() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig13.class);
        //从容器中获取 beanService1
        BeanService beanService1 = context.getBean("beanService1", BeanService.class);
        beanService1.m1();
        //从容器中获取 beanService2
        BeanService beanService2 = context.getBean("beanService2", BeanService.class);
        beanService2.m1();
    }

}
