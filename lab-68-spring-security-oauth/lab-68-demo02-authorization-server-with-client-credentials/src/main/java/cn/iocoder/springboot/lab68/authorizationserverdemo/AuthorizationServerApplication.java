package cn.iocoder.springboot.lab68.authorizationserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考：https://www.iocoder.cn/Fight/ruanyifeng-oauth-grant-types/?self
 * 客户端凭证方式测试
 * 客户端模式，指客户端以自己的名义，而不是以用户的名义，向授权服务器进行认证。
 *
 * 严格地说，客户端模式并不属于 OAuth 框架所要解决的问题。在这种模式中，用户直接向客户端注册，客户端以自己的名义要求授权服务器提供服务，其实不存在授权问题。
 *
 * @author Jaquez
 * @date 2021/10/14 17:00
 */
@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}
