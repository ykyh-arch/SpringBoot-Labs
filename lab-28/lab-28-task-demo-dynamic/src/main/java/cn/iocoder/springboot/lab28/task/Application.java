package cn.iocoder.springboot.lab28.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 动态定时任务事例，实现步骤：Spring Task
 *
 * 缺点：此种方式不能动态启动、删除、暂停定时任务，只是实现了 cron 表达式动态化
 *
 * @author Jaquez
 * @date 2021/07/08 15:15
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
