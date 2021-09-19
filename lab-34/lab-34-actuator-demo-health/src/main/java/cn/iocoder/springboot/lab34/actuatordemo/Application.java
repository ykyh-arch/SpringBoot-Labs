package cn.iocoder.springboot.lab34.actuatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * health 端点{@link HealthIndicator 健康指示器，}
 * @author Jaquez
 * @date 2021/09/18 16:02
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
