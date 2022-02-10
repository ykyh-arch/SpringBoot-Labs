package com.gyx.superscheduled.core;

import com.gyx.superscheduled.common.annotation.SuperScheduledInteriorOrder;
import com.gyx.superscheduled.common.annotation.SuperScheduledOrder;
import com.gyx.superscheduled.common.utils.AnnotationUtils;
import com.gyx.superscheduled.common.utils.proxy.Chain;
import com.gyx.superscheduled.common.utils.proxy.Point;
import com.gyx.superscheduled.common.utils.proxy.ProxyUtils;
import com.gyx.superscheduled.core.RunnableInterceptor.RunnableBaseInterceptor;
import com.gyx.superscheduled.core.RunnableInterceptor.SuperScheduledRunnable;
import com.gyx.superscheduled.core.RunnableInterceptor.strengthen.BaseStrengthen;
import com.gyx.superscheduled.exception.SuperScheduledException;
import com.gyx.superscheduled.model.ScheduledSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 初始化自定义的定时任务运行器
 *
 * @author Jaquez
 * @date 2022/02/10 15:10
 */
@DependsOn("threadPoolTaskScheduler") // 使用 @DependsOn 注解强制依赖 threadPoolTaskScheduler 类
@Component
public class SuperScheduledApplicationRunner implements ApplicationRunner, ApplicationContextAware { // 实现 ApplicationRunner 接口，在所有 bean 初始化结束之后，运行自定义逻辑
    protected final Log logger = LogFactory.getLog(getClass());
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private ApplicationContext applicationContext;

    /**
     * 定时任务配置管理器
     */
    @Autowired
    private SuperScheduledConfig superScheduledConfig;
    /**
     * 定时任务执行线程
     */
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public void run(ApplicationArguments args) {
        // 1.定时任务配置管理器中缓存定时任务执行线程
        superScheduledConfig.setTaskScheduler(threadPoolTaskScheduler);
        // 2.获取所有定时任务源数据
        Map<String, ScheduledSource> nameToScheduledSource = superScheduledConfig.getNameToScheduledSource();
        // 逐一处理定时任务
        for (String name : nameToScheduledSource.keySet()) {
            // 3.获取定时任务源数据
            ScheduledSource scheduledSource = nameToScheduledSource.get(name);
            // 4.获取所有增强类（Aop 代理逻辑实现）
            String[] baseStrengthenBeanNames = applicationContext.getBeanNamesForType(BaseStrengthen.class);
            // 5.创建执行控制器
            SuperScheduledRunnable runnable = new SuperScheduledRunnable();
            runnable.setMethod(scheduledSource.getMethod());
            runnable.setBean(scheduledSource.getBean());
            // 6.逐一处理增强类，将增强器代理成 point，后面会基于它生成代理类执行代理业务逻辑
            List<Point> points = new ArrayList<>(baseStrengthenBeanNames.length);
            for (String baseStrengthenBeanName : baseStrengthenBeanNames) {
                // 7.将增强器代理成 point
                Object baseStrengthenBean = applicationContext.getBean(baseStrengthenBeanName);
                // 获取执行顺序
                SuperScheduledOrder orderAnnotation = baseStrengthenBean.getClass().getAnnotation(SuperScheduledOrder.class);
                SuperScheduledInteriorOrder interiorOrderAnnotation = baseStrengthenBean.getClass().getAnnotation(SuperScheduledInteriorOrder.class);
                // 创建代理，基于 CGlib 生成代理逻辑
                Point proxy = ProxyUtils.getInstance(Point.class, new RunnableBaseInterceptor(baseStrengthenBean, runnable));
                proxy.setOrder(orderAnnotation == null ? 0 : orderAnnotation.value());
                proxy.setInteriorOrder(interiorOrderAnnotation == null ? null : interiorOrderAnnotation.value());
                proxy.setSuperScheduledName(name);
                proxy.setScheduledSource(scheduledSource);
                // 8.所有的 points 连接起来
                points.add(proxy);
            }
            // 按照执行顺序排序
            AnnotationUtils.superScheduledOrderSort(points);
            // 将 point 形成调用链
            runnable.setChain(new Chain(points));
            // 将任务添加缓存中
            superScheduledConfig.addRunnable(name, runnable::invoke);
            // 执行方法，将执行逻辑封装并缓存到定时任务配置管理器中
            try {
                // 8.启动定时任务
                ScheduledFuture<?> schedule = ScheduledFutureFactory.create(threadPoolTaskScheduler
                        ,scheduledSource, runnable::invoke);
                // 将线程回调钩子存到任务配置管理器中
                superScheduledConfig.addScheduledFuture(name, schedule);
                logger.info(df.format(LocalDateTime.now()) + "任务[" + name + "]已经启动...");

            } catch (Exception e) {
                throw new SuperScheduledException("任务[" + name + "]启动失败，错误信息：" + e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
