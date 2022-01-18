package cn.iocoder.springboot.lab61.catdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类，演示自定义注解方法监控管理
 *
 * 更多集成插件参考：https://github.com/dianping/cat/tree/master/integration
 *
 * @author Jaquez
 * @date 2022/01/18 15:30
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("server.port", "9093"); // 端口 9093
        SpringApplication.run(Application.class, args);
    }

}
