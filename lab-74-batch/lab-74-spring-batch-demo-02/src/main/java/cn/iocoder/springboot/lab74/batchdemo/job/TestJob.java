package cn.iocoder.springboot.lab74.batchdemo.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 测试任务类
 *
 * @author jaquez
 * @date 2021/07/08 16:25
 **/
@Component
@DisallowConcurrentExecution // 作业不并发，支持集群模式
public class TestJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info(String.format("这是一个定时任务，执行时间：%s", System.currentTimeMillis()));
    }

}
