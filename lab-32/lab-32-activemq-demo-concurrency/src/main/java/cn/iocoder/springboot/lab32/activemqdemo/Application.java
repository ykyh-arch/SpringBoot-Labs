package cn.iocoder.springboot.lab32.activemqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 并发消费演示示例，入口类
 *
 * @author Jaquez
 * @date 2021/12/16 13:58
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
