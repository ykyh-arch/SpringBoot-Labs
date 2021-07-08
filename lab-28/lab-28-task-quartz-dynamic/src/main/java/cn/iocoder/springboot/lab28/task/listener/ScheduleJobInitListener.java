package cn.iocoder.springboot.lab28.task.listener;

import cn.iocoder.springboot.lab28.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化监听器
 * CommandLineRunner类似Spring框架的ApplicationListener监听器。参考：lab-74-batch-demo启动类
 * @author jaquez
 * @date 2021/07/08 15:24
 **/
@Component
@Order(value = 1) // 指定在同一个spring上下文中执行顺序
public class ScheduleJobInitListener implements CommandLineRunner {

    @Autowired
    TaskService scheduleJobService;

    @Override
    public void run(String... arg0) throws Exception {
        try {
            scheduleJobService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
