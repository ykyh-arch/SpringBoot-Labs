package cn.iocoder.springboot.lab37.loggingdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 多环境的日志配置演示示例
 *
 * @author Jaquez
 * @date 2021/11/19 18:03
 */
@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // 打印日志
        logger.debug("just do it");
    }

}
