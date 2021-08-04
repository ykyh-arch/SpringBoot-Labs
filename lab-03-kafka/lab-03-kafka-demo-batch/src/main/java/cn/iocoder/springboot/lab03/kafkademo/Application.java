package cn.iocoder.springboot.lab03.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 批量发送消息测试，Kafka提供了一个 RecordAccumulator 消息收集器，批量发送消息，当满足以下三个条件触发。
 * 【数量】batch-size ：超过收集的消息数量的最大条数。
 * 【空间】buffer-memory ：超过收集的消息占用的最大内存。
 * 【时间】linger.ms ：超过收集的时间的最大等待时长，单位：毫秒。
 * @author Jaquez
 * @date 2021/08/04 14:21
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
