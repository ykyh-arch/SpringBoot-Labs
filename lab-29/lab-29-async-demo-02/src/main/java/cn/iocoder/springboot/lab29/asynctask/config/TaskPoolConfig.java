package cn.iocoder.springboot.lab29.asynctask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务线程池配置类
 *
 * @author jaquez
 * @date 2021/12/30 09:56
 **/
// @EnableAsync
// @Configuration
public class TaskPoolConfig {

    // 执行器名称
    public static final String MY_TASK_EXECUTOR = "myTaskExecutor";

    // @Bean(name = AsyncExecutionAspectSupport.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    @Bean(name = MY_TASK_EXECUTOR)
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(10);
        // 最大线程数
        executor.setMaxPoolSize(20);
        // 队列容量
        executor.setQueueCapacity(200);
        // 活跃时间
        executor.setKeepAliveSeconds(60);
        // 线程名字前缀
        executor.setThreadNamePrefix("myTaskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
