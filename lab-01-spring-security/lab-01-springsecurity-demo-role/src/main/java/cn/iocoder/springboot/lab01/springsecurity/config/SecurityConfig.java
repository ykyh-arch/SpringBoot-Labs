package cn.iocoder.springboot.lab01.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * 配置类
 * @author Jaquez
 * @date 2021/09/25 16:57
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启 Spring Security 的注解，实现权限控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置 URL 的权限控制
     * @author Jaquez
     * @date 2021/09/25 17:19
     * @param http
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 配置请求地址的权限
                .authorizeRequests()
                    .antMatchers("/test/demo").permitAll() // 所有用户可访问
                    .antMatchers("/test/admin").hasRole("ADMIN") // 需要 ADMIN 角色
                    .antMatchers("/test/normal").access("hasRole('ROLE_NORMAL')") // 需要 NORMAL 角色。
                    // 任何请求，访问的用户都需要经过认证
                    .anyRequest().authenticated()
                .and()
                // 设置 Form 表单登陆
                .formLogin()
//                    .loginPage("/login") // 登陆 URL 地址，可以设置自定义的登录页面
                    .permitAll() // 所有用户可访问
                .and()
                // 配置退出相关
                .logout()
//                    .logoutUrl("/logout") // 退出 URL 地址
                    .permitAll(); // 所有用户可访问
    }

    /**
     * 重写认证管理器
     * @author Jaquez
     * @date 2021/09/25 17:00
     * @param auth
     * @return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                // 使用内存中的 InMemoryUserDetailsManager Bean 对象，提供用户的认证信息
                inMemoryAuthentication()
                // 不使用 PasswordEncoder 密码编码器
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                // 配置 admin 用户
                .withUser("admin").password("123456").roles("ADMIN")
                // 配置 normal 用户
                .and().withUser("normal").password("123456").roles("NORMAL");
    }

}
