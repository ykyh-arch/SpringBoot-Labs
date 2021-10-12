package cn.iocoder.springboot.lab01.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Security 实战演示，参考若依项目
 * @author Jaquez
 * @date 2021/09/25 16:41
 */
@SpringBootApplication
@MapperScan("cn.iocoder.springboot.lab01.springsecurity.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
