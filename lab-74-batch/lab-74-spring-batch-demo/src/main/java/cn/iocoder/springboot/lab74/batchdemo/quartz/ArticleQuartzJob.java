package cn.iocoder.springboot.lab74.batchdemo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ArticleQuartzJob 配置
 *
 * @author Jaquez
 * @date 2022/02/24 16:46
 */
@Slf4j
@Component
@DisallowConcurrentExecution // 定时调度分布式下同一时间只有一个执行
public class ArticleQuartzJob extends QuartzJobBean {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobLocator jobLocator;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            // 获取指定 Job
            Job job = jobLocator.getJob("articleJob");
            //手动启动批处理，执行一个 JobInstance
            jobLauncher.run(job, new JobParametersBuilder()
                    .addDate("startTime", new Date()) // 防止任务执行完，再次执行时报错
                    .addString("executedTime", "2022-11-11 16:21:01")
                    .toJobParameters());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("任务[articleJob]启动失败，错误信息:{}", e.getMessage());
        }
    }
}