package cn.iocoder.springboot.lab74.batchdemo.job;

import cn.hutool.core.date.DateUtil;
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
 * DemoBatchJob 配置，批处理演示Job类，批量从文章表获取数据插入文章详情表
 *
 * @author Jaquez
 * @date 2022/02/24 16:46
 */
@Slf4j
@Component
@DisallowConcurrentExecution // 定时调度分布式下同一时间只有一个执行
public class DemoBatchJob extends QuartzJobBean {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobLocator jobLocator;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            // 获取指定 Job
            Job job = jobLocator.getJob("articleJob");
            // 手动启动批处理，执行一个 JobInstance
            jobLauncher.run(job, new JobParametersBuilder()
                    .addDate("startTime", new Date()) // 防止任务执行完，再次执行时报错，需要添加一个时间戳
                    .addString("executedTime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
                    .toJobParameters());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("任务[articleJob]启动失败，错误信息:{}", e.getMessage());
        }
    }
}