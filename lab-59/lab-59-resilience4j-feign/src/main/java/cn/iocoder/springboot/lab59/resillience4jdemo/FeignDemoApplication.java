package cn.iocoder.springboot.lab59.resillience4jdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 入口类，使用 feign 整合 resilience4j，实现熔断、降级、限流等功能演示示例
 *
 * @author Jaquez
 * @date 2021/11/18 10:30
 */
@SpringBootApplication
public class FeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args);
    }

}
