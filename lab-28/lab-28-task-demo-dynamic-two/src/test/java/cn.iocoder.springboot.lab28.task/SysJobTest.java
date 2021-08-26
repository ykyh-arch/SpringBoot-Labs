package cn.iocoder.springboot.lab28.task;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import cn.iocoder.springboot.lab28.task.entity.SysJob;
import cn.iocoder.springboot.lab28.task.enums.JobStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 定时任务测试类
 *
 * @author jaquez
 * @date 2021/08/26 17:13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SysJobTest {

    @Autowired
    private cn.iocoder.springboot.lab28.task.service.SysJobService sysJobService;

    @Test
    public void testInsert() throws InterruptedException {
        SysJob sysJob = new SysJob();
        sysJob.setBeanName("testTask");
        sysJob.setMethodName("execute");
        // 无参
        // sysJob.setMethodParams("");
        // 有参
        sysJob.setMethodParams("sunwukong");
        sysJob.setCronExpression("*/5 * * * * ?");
        // sysJob.setRemark("这是一个无参定时任务 bean 对象");
        sysJob.setRemark("这是一个有参定时任务 bean 对象");
        sysJob.setJobStatus(JobStatusEnum.NORAML.getCode());
        sysJob.setCreateTime(new Date());
        sysJob.setUpdateTime(new Date());
        sysJobService.insert(sysJob);

        new CountDownLatch(1).await();
    }

    @Test
    public void testUpdate() throws InterruptedException {
        SysJob sysJob = new SysJob();
        sysJob.setJobId(2);
        sysJob.setBeanName("testTask");
        sysJob.setMethodName("execute");
        sysJob.setMethodParams("sunwukong");
        sysJob.setCronExpression("*/3 * * * * ?");
        sysJob.setRemark("这是一个有参定时任务 bean 对象");
        sysJob.setJobStatus(JobStatusEnum.NORAML.getCode());
        sysJob.setUpdateTime(new Date());
        sysJobService.update(sysJob);

        new CountDownLatch(1).await();
    }

    @Test
    public void testDelete() throws InterruptedException {

        sysJobService.delete(1);

        new CountDownLatch(1).await();
    }

    @Test
    public void startOrStop() throws InterruptedException {

        sysJobService.startOrStop(1);

        new CountDownLatch(1).await();
    }

}
