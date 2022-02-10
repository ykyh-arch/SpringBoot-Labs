package cn.iocoder.springboot.lab28.task.taskclass;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试定时任务类
 *
 * @author jaquez
 * @date 2022/02/09 16:59
 **/
@Component
public class TestTask {

    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "0/2 * * * * ?")
    public void robReceiveExpireTask() {
        System.out.println(df.format(LocalDateTime.now()) + " 测试跑起来了~");
    }
}
