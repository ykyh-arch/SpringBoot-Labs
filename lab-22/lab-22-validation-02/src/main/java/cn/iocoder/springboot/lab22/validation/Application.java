package cn.iocoder.springboot.lab22.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 测试 Spring Validator 接口进行校验，参考：https://www.shouce.ren/api/spring2.5/ch05s02.html
 * @author Jaquez
 * @date 2021/09/15 15:22
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
