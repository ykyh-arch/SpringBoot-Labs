package cn.iocoder.springboot.lab77.ratelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * 单机版限流（RateLimiter、Semaphore），参考：https://dominick-li.blog.csdn.net/article/details/106227451
 *
 * @author Jaquez
 * @date 2021/08/27 14:41
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
