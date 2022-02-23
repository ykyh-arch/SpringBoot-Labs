package cn.iocoder.springboot.lab28.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考：https://mp.weixin.qq.com/s/7TV0iZF8d0GEb_qbqImUVQ
 *
 * 动态定时任务，实现步骤：
 * ①启动项目，启动 task 监听
 * ②读取数据库，将开启的任务 job 和 trigger 加载到 scheduler 调度器
 * ③根据任务调度运行 job 类
 * ④每次运行利用 AdaptableJobFactory 实例化 job 类，以便注入要运行的 service
 * <p>
 *
 * SpringBoot + SpringBatch + Quartz整合定时批量任务参考：https://mp.weixin.qq.com/s/IwkgluQ4jiGBGlvKRomwGg
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
