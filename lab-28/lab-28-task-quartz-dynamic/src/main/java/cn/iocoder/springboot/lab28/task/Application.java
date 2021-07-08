package cn.iocoder.springboot.lab28.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考：https://mp.weixin.qq.com/s/7TV0iZF8d0GEb_qbqImUVQ
 * 动态定时任务事例，实现步骤：
 * ①启动项目，启动task监听
 * ②读取数据库，将开启的任务job和trigger加载到scheduler调度器
 * ③根据任务调度运行job类
 * ④每次运行利用AdaptableJobFactory实例化job类，以便注入要运行的service
 * @author Jaquez
 * @date 2021/07/08 15:15
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
