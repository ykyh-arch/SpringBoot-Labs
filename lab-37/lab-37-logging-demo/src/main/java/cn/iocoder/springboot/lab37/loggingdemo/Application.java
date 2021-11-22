package cn.iocoder.springboot.lab37.loggingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类
 * 日志门面（slf4j、jcl、jboss-logging）
 * 日志实现（jul、apache log4j1、log4j2、logback）
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
