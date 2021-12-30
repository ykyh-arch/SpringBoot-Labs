package cn.iocoder.springboot.lab29.asynctask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 自定义线程池，覆盖默认的 @Async 实现演示示例（功能同 async-two）
 *
 * 参考：https://mp.weixin.qq.com/s/7M9eZUNcXLm6767FVvnb2w
 *
 * @author jaquez
 * @date 2021/11/04 18:16
 **/
@SpringBootApplication
@EnableAsync // 开启 @Async 的支持
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
