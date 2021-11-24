package cn.iocoder.springboot.lab81.arthas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * arthas 演示示例
 *
 * @author Jaquez
 * @date 2021/11/24 16:15
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.iocoder.springboot.lab81.arthas.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
