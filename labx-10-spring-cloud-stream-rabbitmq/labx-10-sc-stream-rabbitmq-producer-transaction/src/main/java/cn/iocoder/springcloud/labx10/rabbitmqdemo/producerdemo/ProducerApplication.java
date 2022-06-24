package cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo;

import cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.message.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 启动类
 * 
 * @author Jaquez
 * @date 2022/06/23 16:26
 */
@SpringBootApplication
@EnableBinding(MySource.class)
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
