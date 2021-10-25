package cn.iocoder.springboot.lab04.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 批量发送消息示例
 * Spring-AMQP 通过 BatchingRabbitTemplate 提供批量发送消息的功能。如下是三个条件，满足任一即会批量发送：
 * 【数量】batchSize ：超过收集的消息数量的最大条数。
 * 【空间】bufferLimit ：超过收集的消息占用的最大内存。
 * 【时间】timeout ：超过收集的时间的最大等待时长，单位：毫秒。😈 不过要注意，这里的超时开始计时的时间，是以最后一次发送时间为起点。也就说，每调用一次发送消息，都以当前时刻开始计时，重新到达 timeout 毫秒才算超时。
 * @author Jaquez
 * @date 2021/10/25 16:58
 */
@SpringBootApplication
@EnableAsync // 开启异步
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
