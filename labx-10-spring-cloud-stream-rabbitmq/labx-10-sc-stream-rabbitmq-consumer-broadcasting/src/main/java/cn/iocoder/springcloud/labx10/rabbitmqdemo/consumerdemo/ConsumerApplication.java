package cn.iocoder.springcloud.labx10.rabbitmqdemo.consumerdemo;

import cn.iocoder.springcloud.labx10.rabbitmqdemo.consumerdemo.listener.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/06/23 14:24
 */
@SpringBootApplication
@EnableBinding(MySink.class)
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
