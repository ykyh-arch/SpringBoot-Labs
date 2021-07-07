package cn.iocoder.springboot.lab48.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 通过使用两个类加载器来提供了重启技术。
 * 不改变的类（例如，第三方 jar）被加载到 base 类加载器中。
 * 经常处于开发状态的类被加载到 restart 类加载器中。
 * 快速重启要比冷启动要快得多，因为省去 base 类加载器的处理步骤，并且可以直接使用。
 *
 * 利用IDEA 的HotSWap热部署插件，自动编译：在Edit Configurations -> Running Application Update Polices中配置
 * JRebel热部署参考：https://www.iocoder.cn/Fight/IDEA-JRebel-plug-in-hot-deployment/?self
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
