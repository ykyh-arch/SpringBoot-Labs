package cn.iocoder.springboot.lab28.task.config;

import cn.iocoder.springboot.lab28.task.entity.SysJob;
import cn.iocoder.springboot.lab28.task.enums.JobStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 系统定时任务启动类，随程序的启动而运行
 *
 * @author jaquez
 * @date 2021/08/26 16:51
 **/
@Component
public class SysJobRunner  implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysJobRunner.class);

    @Autowired
    private cn.iocoder.springboot.lab28.task.mapper.SysJobMapper sysJobMapper;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务  
        List<SysJob> jobList = sysJobMapper.getSysJobListByStatus(JobStatusEnum.NORAML.getCode());
        if (!CollectionUtils.isEmpty(jobList)) {
            for (SysJob job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(task, job.getCronExpression());
            }
            logger.info("定时任务已加载完毕...");
        }
    }
    
}
