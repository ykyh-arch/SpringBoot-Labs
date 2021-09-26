package cn.iocoder.springboot.lab01.springsecurity.conf;

import cn.iocoder.springboot.lab01.springsecurity.filter.JwtAuthenticationTokenFilter;
import cn.iocoder.springboot.lab01.springsecurity.handler.*;
import cn.iocoder.springboot.lab01.springsecurity.userdetails.SelfUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 权限管理配置类
 *
 * @author jaquez
 * @date 2021/09/25 21:24
 **/
@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {

    @Value("${token.expiration-seconds}")
    private Integer expirationSeconds;

    // 未登陆时返回 JSON 格式的数据给前端（否则为 html）
    @Autowired
    AjaxAuthenticationEntryPointHanlder authenticationEntryPointHanlder;

    // 登录成功返回的 JSON 格式数据给前端（否则为 html）
    @Autowired
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    //  登录失败返回的 JSON 格式数据给前端（否则为 html）
    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler;

    // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
    @Autowired
    AjaxLogoutSuccessHandler logoutSuccessHandler;

    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;

    // 自定义 user 对象服务类
    @Autowired
    SelfUserDetailsService userDetailsService;

    // JWT 拦截器
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    // 自定义认证提供类
    @Autowired
    AuthenticationProviderHandler authenticationProviderHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 加入自定义的安全认证
        auth
        .authenticationProvider(authenticationProviderHandler)
        .userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // 对 http api 进行配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 去掉 CSRF
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/test/login").permitAll() // 放行部分接口
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭 session
                .and()

                .httpBasic().authenticationEntryPoint(authenticationEntryPointHanlder)

                .and()
                .authorizeRequests()

                .anyRequest()
                .access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证

                .and()
                .formLogin()  //开启登录
                .successHandler(authenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler) // 退出登录
                .permitAll();

        // 记住我 5 分钟
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService).tokenValiditySeconds(expirationSeconds);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter

    }
}

