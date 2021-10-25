package cn.iocoder.springboot.lab04.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *  Rabbit 入门测试， {@link RabbitAutoConfiguration }
 * @author Jaquez
 * @date 2021/10/25 11:12
 */
@SpringBootApplication
@EnableAsync // 开启异步
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
