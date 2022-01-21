package cn.iocoder.springboot.lab63.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 服务消费者演示示例
 *
 * @author Jaquez
 * @date 2022/01/21 14:20
 */
@SpringBootApplication
@ImportResource("classpath:motan.xml")
public class ConsumerApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(ConsumerApplication.class, args);;
    }

}
