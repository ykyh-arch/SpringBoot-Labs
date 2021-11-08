package cn.iocoder.springboot.lab58.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务，对外暴露端口为：18080
 * @author Jaquez
 * @date 2021/11/07 15:41
 */
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        // 设置端口
        System.setProperty("server.port", "18080");

        // 应用启动
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
