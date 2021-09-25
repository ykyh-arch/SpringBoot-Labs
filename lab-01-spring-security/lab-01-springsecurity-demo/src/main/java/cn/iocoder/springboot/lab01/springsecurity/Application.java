package cn.iocoder.springboot.lab01.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

/**
 * Spring Security 入门测试，默认情况下，{@link UserDetailsServiceAutoConfiguration } 自动化配置类，会创建一个内存级别的 InMemoryUserDetailsManager Bean 对象，提供认证的用户信息。
 * @author Jaquez
 * @date 2021/09/25 16:41
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
