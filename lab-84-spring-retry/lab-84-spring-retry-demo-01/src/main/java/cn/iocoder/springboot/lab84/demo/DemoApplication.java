package cn.iocoder.springboot.lab84.demo;

import cn.iocoder.springboot.lab84.demo.service.TestRetryService;
import cn.iocoder.springboot.lab84.demo.task.GuavaRetryTest;
import cn.iocoder.springboot.lab84.demo.task.SpringRetryTemplateTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Spring retry 测试使用，参考：https://mp.weixin.qq.com/s/_12vgPi2sDQ6sjJeXLKr4A
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
        // bean.test(0);

        SpringRetryTemplateTest springRetryTemplateTest = run.getBean(SpringRetryTemplateTest.class);
        // springRetryTemplateTest.test();

        GuavaRetryTest guavaRetryTest = run.getBean(GuavaRetryTest.class);
        guavaRetryTest.test();
    }
}
