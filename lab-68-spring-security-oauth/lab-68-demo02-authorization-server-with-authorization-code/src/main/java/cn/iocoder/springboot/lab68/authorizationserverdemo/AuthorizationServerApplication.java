package cn.iocoder.springboot.lab68.authorizationserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 授权码模式测试
 * 授权码模式，是功能最完整、流程最严密的授权模式。它的特点就是通过客户端的后台服务器，与授权务器进行互动。
 *
 * @author Jaquez
 * @date 2021/10/14 15:39
 */
@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}
