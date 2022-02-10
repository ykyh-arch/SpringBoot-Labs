package cn.iocoder.springboot.lab28.task;

import com.gyx.superscheduled.core.SuperScheduledManager;
import com.gyx.superscheduled.model.ScheduledLogFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 定时任务测试类，参考自：https://github.com/guoyixing/super-scheduled
 *
 * @author jaquez
 * @date 2022/02/09 19:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperScheduledTest {

    // 直接注入管理器
    @Autowired
    private SuperScheduledManager superScheduledManager;

    // 测试类
    @Test
    public void test() throws InterruptedException {
        // 获取所有定时任务（默认是 bean 名称.方法名）
        List<String> allSuperScheduledName = superScheduledManager.getAllSuperScheduledName();
        String name = allSuperScheduledName.get(0);
        // 终止定时任务
        superScheduledManager.cancelScheduled(name);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("任务名：" + name);
        // 启动定时任务
        superScheduledManager.addCronScheduled(name, "0/2 * * * * ?");
        // 获取启动汇总的定时任务
        List<String> runScheduledName = superScheduledManager.getRunScheduledName();
        runScheduledName.forEach(System.out::println);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 修改定时任务执行周期
        superScheduledManager.setScheduledCron(name, "0/5 * * * * ?");
        // 日志文件信息
        // superScheduledManager.getScheduledLogFiles().forEach(System.out::println);
        // 使程序阻塞
        new CountDownLatch(1).await();
    }

}
