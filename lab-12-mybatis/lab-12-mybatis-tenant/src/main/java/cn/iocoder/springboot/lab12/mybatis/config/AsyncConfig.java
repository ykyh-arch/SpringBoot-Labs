package cn.iocoder.springboot.lab12.mybatis.config;

import com.alibaba.ttl.TtlRunnable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 异步配置
 *
 * @author jaquez
 * @date 2022/01/07 16:54
 **/
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public BeanPostProcessor executorBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof ThreadPoolTaskExecutor)) {
                    return bean;
                }
                // 线程池执行器配置 TransmittableThreadLocal，作用：可以实现在线程池与主线程间传递信息
                ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) bean;
                executor.setTaskDecorator(TtlRunnable::get);
                return executor;
            }

        };
    }

}
