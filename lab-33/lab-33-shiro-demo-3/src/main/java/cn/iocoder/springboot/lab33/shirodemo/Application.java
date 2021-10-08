package cn.iocoder.springboot.lab33.shirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * SpringBoot 整合 Shiro 实现动态权限加载更新 + Session 共享 + 单点登录，密码进行了 SHA256 加密，shiro 也需要对密码进行 SHA256 加密
 * @author Jaquez
 * @date 2021/10/05 11:19
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.iocoder.springboot.lab33.shirodemo.dao")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }

}
