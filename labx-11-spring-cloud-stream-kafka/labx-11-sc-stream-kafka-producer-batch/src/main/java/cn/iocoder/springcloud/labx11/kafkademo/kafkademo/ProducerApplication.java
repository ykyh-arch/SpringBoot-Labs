package cn.iocoder.springcloud.labx11.kafkademo.kafkademo;

import cn.iocoder.springcloud.labx11.kafkademo.kafkademo.message.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/07/06 14:41
 */
@SpringBootApplication
@EnableBinding(MySource.class)
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
