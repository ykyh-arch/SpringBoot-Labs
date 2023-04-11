package cn.iocoder.springboot.lab37.loggingtlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类
 * 日志门面（slf4j、jcl、jboss-logging）
 * 日志实现（jul、apache log4j1、log4j2、logback）
 * 轻量级的分布式日志标记追踪神器-Tlog 测试使用，参考：https://mp.weixin.qq.com/s/mnt0mPsAM8msV3q7gsQjIg
 *
 * @author Jaquez
 * @date 2021/11/19 14:49
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
