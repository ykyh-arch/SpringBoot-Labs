package cn.iocoder.springboot.lab28.task.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 测试任务类
 *
 * @author jaquez
 * @date 2021/07/08 16:25
 **/
@DisallowConcurrentExecution //作业不并发
@Component
public class TestTask implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("这是一个定时任务，执行时间："+ System.currentTimeMillis());
    }

}
