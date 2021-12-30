package cn.iocoder.springboot.lab29.asynctask.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步配置类，扩展方式一：
 * <p>
 * 测试时关闭其他配置项，否则报：Only one AsyncConfigurer may exist
 *
 * @author jaquez
 * @date 2021/12/28 15:28
 **/
// @Configuration
// @EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 执行器名称
    public static final String KING_ASYNC_EXECUTOR = "kingAsyncExecutor";

    /**
     * 自定义执行器名称
     *
     * @return org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
     * @author Jaquez
     * @date 2021/12/28 16:31
     */
    @Bean(name = KING_ASYNC_EXECUTOR)
    public ThreadPoolTaskExecutor executor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int corePoolSize = 10;
        executor.setCorePoolSize(corePoolSize);
        int maxPoolSize = 50;
        executor.setMaxPoolSize(maxPoolSize);
        int queueCapacity = 10;
        executor.setQueueCapacity(queueCapacity);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        String threadNamePrefix = "kingAsyncExecutor-";
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // int awaitTerminationSeconds = 5;
        // executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        executor.initialize();
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> logger.error("[getAsyncUncaughtExceptionHandler][method({}) params({}) 发生异常]",
                method, params, ex);
    }
}
