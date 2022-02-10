package cn.iocoder.springboot.lab28.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 通用版定时任务解决方案，参考自：https://mp.weixin.qq.com/s/fJAFvFTVlnuTVjTXlGNWRA
 *
 * @author Jaquez
 * @date 2021/07/08 15:15
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
