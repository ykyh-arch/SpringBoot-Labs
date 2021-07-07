package cn.iocoder.springboot.lab74.batchdemo.quartz;

import cn.iocoder.springboot.lab74.batchdemo.job.ArticleQuartzJob;
import cn.iocoder.springboot.lab74.batchdemo.utils.QuartzUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * QuartzJobInit 初始化
 */
@Component
public class QuartzJobInit implements CommandLineRunner {

    @Autowired
    private QuartzUtils quartzUtils;

    @Override
    public void run(String... args) throws Exception {
        quartzUtils.addSingleJob(ArticleQuartzJob.class, "articleJob", 60);
    }
}
