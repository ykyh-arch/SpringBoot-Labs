package com.gyx.superscheduled.core.RunnableInterceptor.strengthen;

import com.gyx.superscheduled.model.ScheduledRunningContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 基础增强类接口
 *
 * @author Jaquez
 * @date 2022/02/11 11:01
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public interface BaseStrengthen {
    /**
     * 前置强化方法
     *
     * @param bean    bean实例（或者是被代理的bean）
     * @param method  执行的方法对象
     * @param args    方法参数
     * @param context 任务线程运行时的上下文
     */
    void before(Object bean, Method method, Object[] args, ScheduledRunningContext context);

    /**
     * 后置强化方法
     * 出现异常不会执行
     * 如果未出现异常，在afterFinally方法之后执行
     *
     * @param bean    bean实例（或者是被代理的bean）
     * @param method  执行的方法对象
     * @param args    方法参数
     * @param context 任务线程运行时的上下文
     */
    void after(Object bean, Method method, Object[] args, ScheduledRunningContext context);

    /**
     * 异常强化方法
     *
     * @param bean    bean实例（或者是被代理的bean）
     * @param method  执行的方法对象
     * @param args    方法参数
     * @param context 任务线程运行时的上下文
     */
    void exception(Object bean, Method method, Object[] args, ScheduledRunningContext context);

    /**
     * Finally强化方法，出现异常也会执行
     *
     * @param bean    bean实例（或者是被代理的bean）
     * @param method  执行的方法对象
     * @param args    方法参数
     * @param context 任务线程运行时的上下文
     */
    void afterFinally(Object bean, Method method, Object[] args, ScheduledRunningContext context);
}