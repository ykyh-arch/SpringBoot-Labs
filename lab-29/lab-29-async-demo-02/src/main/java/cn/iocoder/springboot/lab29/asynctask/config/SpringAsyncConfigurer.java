package cn.iocoder.springboot.lab29.asynctask.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 自定义的异常配置类，扩展方式二：
 *
 * @author jaquez
 * @date 2021/12/28 18:00
 **/
@Configuration
@EnableAsync
public class SpringAsyncConfigurer extends AsyncConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 执行器名称
    public static final String ASYNC_EXECUTOR = "asyncExecutor";

    @Bean(name = ASYNC_EXECUTOR)
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(3);
        threadPool.setMaxPoolSize(3);
        threadPool.setThreadNamePrefix("asyncExecutor-");
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        threadPool.setAwaitTerminationSeconds(60 * 15);
        return threadPool;
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> logger.error("[getAsyncUncaughtExceptionHandler][method({}) params({}) 发生异常]",
                method, params, ex);
    }

}
