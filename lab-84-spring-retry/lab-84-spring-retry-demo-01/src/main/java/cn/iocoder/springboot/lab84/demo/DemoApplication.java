package cn.iocoder.springboot.lab84.demo;

import cn.iocoder.springboot.lab84.demo.service.TestRetryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Spring retry 测试使用
 *
 * @author Jaquez
 * @date 2022/04/25 19:17
 */
@EnableRetry // 开启重试
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        TestRetryService bean = run.getBean(TestRetryService.class);
        bean.test(0);
    }
}
