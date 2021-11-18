package cn.iocoder.springboot.lab59.resillience4jdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 入口类，演示熔断、限流、舱壁、重试、限时的功能，组合使用的加载顺序为：Retry > CircuitBreaker > RateLimiter > TimeLimiter > Bulkhead
 *
 * @author Jaquez
 * @date 2021/11/18 10:30
 */
@SpringBootApplication
public class DemoApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
