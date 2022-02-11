package com.gyx.superscheduled.core;

import com.gyx.superscheduled.exception.SuperScheduledException;
import com.gyx.superscheduled.model.ScheduledSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.gyx.superscheduled.common.utils.AnnotationUtils.changeAnnotationValue;

/**
 * SuperScheduledPostProcessor 自定义定时任务后置处理器（将 springboot 中的 @Scheduled 中的属性信息赋值给 ScheduledSource，并放入容器中并且停止 springboot 的定时任务）
 *
 * @author Jaquez
 * @date 2022/02/10 15:08
 */
@DependsOn({"superScheduledConfig"}) // 使用 @DependsOn 注解强制依赖 SuperScheduledConfig 类，让 SpringBoot 实例化 SuperScheduledPostProcessor 类之前先实例化 SuperScheduledConfig 类
@Component
@Order
public class SuperScheduledPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    protected final Log logger = LogFactory.getLog(getClass());

    private ApplicationContext applicationContext;

    /**
     * 实例化 bean 之前的操作
     * @param bean bean 实例
     * @param beanName bean 的 Name
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 实例化 bean 之后的操作
     * @param bean bean 实例
     * @param beanName bean 的 Name
     */
    @Override
    public Object postProcessAfterInitialization(Object bean,
                                                 String beanName) throws BeansException {
        // 1.获取配置管理器
        SuperScheduledConfig superScheduledConfig = applicationContext.getBean(SuperScheduledConfig.class);

        // 2.获取当前实例化完成的 bean 的所有方法
        Method[] methods = bean.getClass().getDeclaredMethods();
        // 循环处理对每个方法逐一处理
        if (methods.length > 0) {
            for (Method method : methods) {
                // 3.尝试在该方法上获取 @Scheduled 注解（ SpringBoot 的定时任务注解）
                Scheduled annotation = method.getAnnotation(Scheduled.class);
                // 如果无法获取到 @Scheduled 注解，就跳过这个方法
                if (annotation == null) {
                    continue;
                }
                // 4.创建定时任务的源属性
                // 创建定时任务的源属性（用来记录定时任务的配置，初始化的时候记录的是注解上原本的属性）
                ScheduledSource scheduledSource = new ScheduledSource(annotation, method, bean);
                // 对注解上获取到源属性中的属性进行检测
                if (!scheduledSource.check()) {
                    throw new SuperScheduledException("在" + beanName + "Bean中" + method.getName() + "方法的注解参数错误");
                }
                // 生成定时任务的名称（id），使用beanName+“.”+方法名
                String name = beanName + "." + method.getName();
                // 将以 key-value 的形式，将源数据存入配置管理器中，key：定时任务的名称 value：源数据
                superScheduledConfig.addScheduledSource(name, scheduledSource);
                try {
                    // 5.将原本 SpringBoot 的定时任务取消掉
                    clearOriginalScheduled(annotation);
                } catch (Exception e) {
                    throw new SuperScheduledException("在关闭原始方法" + beanName + method.getName() + "时出现错误");
                }
            }
        }
        // 最后 bean 保持原有返回
        return bean;
    }

    /**
     * 修改注解原先的属性
     * @param annotation 注解实例对象
     * @throws Exception
     */
    private void clearOriginalScheduled(Scheduled annotation) throws Exception {
        changeAnnotationValue(annotation, "cron", Scheduled.CRON_DISABLED);
        changeAnnotationValue(annotation, "fixedDelay", -1L);
        changeAnnotationValue(annotation, "fixedDelayString", "");
        changeAnnotationValue(annotation, "fixedRate", -1L);
        changeAnnotationValue(annotation, "fixedRateString", "");
        changeAnnotationValue(annotation, "initialDelay", -1L);
        changeAnnotationValue(annotation, "initialDelayString", "");
    }


    /**
     * 获取 SpringBoot 的上下文环境
     * @param applicationContext SpringBoot 的上下文
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}