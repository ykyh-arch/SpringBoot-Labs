package cn.iocoder.springboot.lab28.powerjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类，官方文档参考：https://www.yuque.com/powerjob/guidence/intro
 *
 * @author Jaquez
 * @date 2023/03/08 14:55
 */
@EnableScheduling
@SpringBootApplication
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
