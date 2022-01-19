package cn.iocoder.springboot.lab62.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2022/01/19 16:01
 */
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(ConsumerApplication.class, args);;
    }

}
