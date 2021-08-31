package cn.iocoder.springboot.lab77.ratelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot + redis + lua + aop + 自定义注解，处理分布式限流问题
 * 分布式限流，单机版限流（AtomicInteger、RateLimiter、Semaphore）、网关限流
 * @author Jaquez
 * @date 2021/08/27 14:41
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
