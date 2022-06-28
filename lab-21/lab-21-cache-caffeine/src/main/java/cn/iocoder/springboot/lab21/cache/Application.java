package cn.iocoder.springboot.lab21.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Caffeine 缓存测试用例，参考：http://www.mydlq.club/article/56/
 *
 * @author Jaquez
 * @date 2021/08/30 11:26
 */
@EnableCaching
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
