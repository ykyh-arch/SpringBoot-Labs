package cn.iocoder.springboot.lab01.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * 参考：https://www.iocoder.cn/Fight/Separate-SpringBoot-SpringSecurity-JWT-RBAC-from-front-and-rear-to-achieve-user-stateless-request-authentication/?self
 * 前后端分离 SpringBoot + SpringSecurity + JWT + RBAC 实现用户无状态请求验证入门测试
 * 缺点：没有注销 token 登录
 * 解决办法：每次登录，生成 token 放到 Redis 数据库里边，调用接口的时候，先查有没有这个 token，注销时把 token 删除
 * @author Jaquez
 * @date 2021/09/25 16:41
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
