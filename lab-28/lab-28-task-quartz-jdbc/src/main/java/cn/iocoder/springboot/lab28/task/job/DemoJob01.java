package cn.iocoder.springboot.lab28.task.job;

import cn.iocoder.springboot.lab28.task.service.DemoService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

// 保证相同 JobDetail 在多个 JVM 进程中，有且仅有一个节点在执行。
@DisallowConcurrentExecution
public class DemoJob01 extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoService demoService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("[executeInternal][我开始的执行了, demoService 为 ({})]", demoService);
    }

}
