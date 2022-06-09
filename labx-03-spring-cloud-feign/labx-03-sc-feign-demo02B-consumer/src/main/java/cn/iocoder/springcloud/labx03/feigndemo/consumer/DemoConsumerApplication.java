package cn.iocoder.springcloud.labx03.feigndemo.consumer;

import cn.iocoder.springcloud.labx03.feigndemo.consumer.config.DefaultFeignClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/06/09 11:50
 */
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = DefaultFeignClientConfiguration.class)
public class DemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

}
