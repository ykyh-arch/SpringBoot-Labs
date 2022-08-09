package cn.iocoder.springcloud.labx14.rabbitmqdemo.producerdemo;

import cn.iocoder.springcloud.labx14.rabbitmqdemo.producerdemo.message.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/08/09 16:42
 */
@SpringBootApplication
@EnableBinding(MySource.class)
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
