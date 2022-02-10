package cn.iocoder.springboot.lab28.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 动态定时任务事例，重写 org.springframework.scheduling.ScheduledTaskRegistrar 类
 * <p>
 * 实现动态执行定时任务，包括启动、添加、删除、暂停等定时任务
 * <p>
 * 参考：https://mp.weixin.qq.com/s/0P0OMg_YY7bqvLfIkOGe5Q
 * <p>
 * @Scheduled 增强版使用，可实现动态维护，参考：https://mp.weixin.qq.com/s/fJAFvFTVlnuTVjTXlGNWRA
 *
 * @author Jaquez
 * @date 2021/07/08 15:15
 */
@SpringBootApplication
@MapperScan("cn.iocoder.springboot.lab28.task.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
