package cn.iocoder.springboot.lab85.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 数据脱敏插件，参考开源项目实现：https://gitee.com/strong_sea/sensitive-plus
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
