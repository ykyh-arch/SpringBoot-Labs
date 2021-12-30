package cn.iocoder.springboot.lab29.asynctask.service;

import cn.iocoder.springboot.lab29.asynctask.config.AsyncConfiguration;
import cn.iocoder.springboot.lab29.asynctask.config.SpringAsyncConfigurer;
import cn.iocoder.springboot.lab29.asynctask.config.TaskPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 指定执行器名称
    @Async(AsyncConfiguration.KING_ASYNC_EXECUTOR)
    public Integer execute01() {
        logger.info("[execute01]");
        return 1;
    }

    @Async(SpringAsyncConfigurer.ASYNC_EXECUTOR)
    public Integer execute02() {
        logger.info("[execute02]");
        return 2;
    }

    @Async(TaskPoolConfig.MY_TASK_EXECUTOR)
    public Integer execute03() {
        logger.info("[execute3]");
        return 3;
    }

}
