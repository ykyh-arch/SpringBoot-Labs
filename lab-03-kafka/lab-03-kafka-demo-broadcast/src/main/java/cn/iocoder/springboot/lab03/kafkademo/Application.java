package cn.iocoder.springboot.lab03.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 广播消费，广播消费模式下，相同 Consumer Group 的每个 Consumer 实例都接收全量的消息。
 * 不过 Kafka 并不直接提供内置的广播消费的功能！此时，我们只能退而求其次，每个 Consumer 独有一个 Consumer Group ，从而保证都能接收到全量的消息。
 * @author Jaquez
 * @date 2021/08/04 17:28
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
