package cn.iocoder.springboot.labs.lab69.proxy;

import cn.iocoder.springboot.labs.lab69.target.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * DynamicProxyTest JDK 动态代理(dynamic proxy)。基于接口实现方式
 *
 * @author jaquez
 * @date 2022/05/20 14:44
 **/
public class DynamicProxyTest implements InvocationHandler {

    private Test target;

    private DynamicProxyTest(Test target) {
        this.target = target;
    }

    /**
     * 代理对象
     * @param target
     * @return
     */
    public static Test newProxyInstance(Test target) {
        return (Test) Proxy
                .newProxyInstance(DynamicProxyTest.class.getClassLoader(),
                        new Class<?>[] { Test.class },
                        new DynamicProxyTest(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(target, args);
    }
}
