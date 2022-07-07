package cn.iocoder.springcloud.labx11.kafkademo.kafkademo;

import cn.iocoder.springcloud.labx11.kafkademo.kafkademo.message.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/07/05 11:11
 */
@SpringBootApplication
@EnableBinding(MySource.class)
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
