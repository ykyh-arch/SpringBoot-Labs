package cn.iocoder.springboot.lab61.catdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * 入口类，Cat 环境搭建参考：https://www.cnblogs.com/shuo1208/p/11798873.html
 *
 * 踩坑：注意环境的版本问题，参考：https://github.com/dianping/cat/wiki/readme_server
 *
 * 这里搭配使用 mysql 5.6 + tomcat 8.0
 *
 * @author Jaquez
 * @date 2022/01/14 17:03
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        System.setProperty("server.port", "9093"); // 端口 9093
        SpringApplication.run(Application.class, args);
    }

}
