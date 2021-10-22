package cn.iocoder.springboot.lab68.authorizationserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 统一登录入口，参考：https://blog.csdn.net/javazhiyin/article/details/96415735
 * @author Jaquez
 * @date 2021/10/19 16:28
 */
@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}
