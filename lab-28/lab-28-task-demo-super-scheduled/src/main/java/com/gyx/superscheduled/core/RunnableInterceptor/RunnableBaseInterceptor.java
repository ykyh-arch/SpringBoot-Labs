package com.gyx.superscheduled.core.RunnableInterceptor;

import com.gyx.superscheduled.core.RunnableInterceptor.strengthen.BaseStrengthen;
import com.gyx.superscheduled.exception.SuperScheduledException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 使用 cglib 代理增强器，将增强器全部代理成调用链节点 Point
 *
 * @author Jaquez
 * @date 2022/02/10 17:56
 */
public class RunnableBaseInterceptor implements MethodInterceptor {
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * 定时任务执行器
     */
    private SuperScheduledRunnable runnable;
    /**
     * 定时任务增强类
     */
    private BaseStrengthen strengthen;

    /**
     * 增强的业务逻辑，目标对象方法执行会在这里统一增强处理
     *
     * @author Jaquez
     * @date 2022/02/10 16:22
     * @param obj
     * @param method
     * @param args
     * @param methodProxy
     * @return java.lang.Object
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result;
        List<String> methodName = Arrays.asList("invoke", "before", "after", "exception", "afterFinally");
        if (methodName.contains(method.getName())) {
            // 前置强化方法
            strengthen.before(obj, method, args, runnable.getContext());
            try {
                // 调用执行器中的 invoke() 方法
                result = runnable.invoke();
            } catch (Exception e) {
                // //异常强化方法
                strengthen.exception(obj, method, args, runnable.getContext());
                throw new SuperScheduledException(strengthen.getClass() + "中强化执行时发生错误", e);
            } finally {
                // Finally 强化方法，出现异常也会执行
                strengthen.afterFinally(obj, method, args, runnable.getContext());
            }
            // 后置强化方法
            strengthen.after(obj, method, args, runnable.getContext());
        } else {
            // 直接执行方法
            result = methodProxy.invokeSuper(obj, args);
        }
        return result;
    }

    public RunnableBaseInterceptor(Object object, SuperScheduledRunnable runnable) {
        this.runnable = runnable;
        if (BaseStrengthen.class.isAssignableFrom(object.getClass())) {
            this.strengthen = (BaseStrengthen) object;
        } else {
            throw new SuperScheduledException(object.getClass() + "对象不是BaseStrengthen类型");
        }
    }

    public RunnableBaseInterceptor() {

    }
}
