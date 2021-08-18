package cn.iocoder.springboot.lab31.rocketmqdemo;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RocketMQ 测试，参考Kafka对比区别
 * @author Jaquez
 * @date 2021/08/16 11:46
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
