package cn.iocoder.springboot.lab74.batchdemo.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * QuartzJobInit 初始化，使用流程，Quartz 定时启动（Scheduler、Trigger、JobDetail） -》 添加 Job、配置 JobDetail，Trigger（设置QuartzJobName）、定时执行任务 -》传入 Batch Job（Batch Job、 jobLauncher.run(）手动启动
 *
 * @author Jaquez
 * @date 2022/02/24 16:53
 */
@Component
public class QuartzJobInit implements CommandLineRunner {

    @Autowired
    private QuartzUtils quartzUtils;

    @Override
    public void run(String... args) throws Exception {
        quartzUtils.addSingleJob(ArticleQuartzJob.class, "articleJob", 5);
    }
}
