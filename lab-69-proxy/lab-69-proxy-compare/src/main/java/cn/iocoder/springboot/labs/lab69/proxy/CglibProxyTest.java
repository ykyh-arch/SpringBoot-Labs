package cn.iocoder.springboot.labs.lab69.proxy;

import cn.iocoder.springboot.labs.lab69.target.Test;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CglibProxyTest Cglib 动态代理 (cglib proxy)。基于实现类实现的
 *
 * @author jaquez
 * @date 2022/05/20 14:53
 **/
public class CglibProxyTest implements MethodInterceptor {

    private CglibProxyTest() {
    }

    public static <T extends Test> Test newProxyInstance(Class<T> targetInstanceClazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetInstanceClazz);
        enhancer.setCallback(new CglibProxyTest());
        return (Test) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        return proxy.invokeSuper(obj, args);
    }
}
