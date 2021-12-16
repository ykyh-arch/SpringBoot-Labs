package cn.iocoder.springboot.lab32.activemqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消费者重试示例，入口类
 *
 * @author Jaquez
 * @date 2021/12/16 15:03
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
