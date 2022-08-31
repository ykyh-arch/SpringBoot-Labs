package cn.iocoder.springboot.lab85.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot 利用 jackson 实现数据脱敏，参考：https://lizhou.blog.csdn.net/article/details/125367694，亦可参考： lab-12-mybatis-plus
 *
 * @author Jaquez
 * @date 2022/04/25 19:17
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
    }
}
