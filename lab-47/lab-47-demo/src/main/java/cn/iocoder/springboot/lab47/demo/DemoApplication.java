package cn.iocoder.springboot.lab47.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;

/**
 * 更多自定义的starter可以参考开源项目onemall：https://github.com/YunaiV/onemall/tree/master/common
 * 拓展 spring 知识清单：https://www.iocoder.cn/Fight/Give-you-a-list-of-Spring-Boot-knowledge/?self
 * @author Jaquez
 * @date 2021/07/17 14:17
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
