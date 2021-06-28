package cn.iocoder.springboot.lab73.aopdemo.config;

import cn.iocoder.springboot.lab73.aopdemo.interceptor.LogInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 日志配置类
 *
 * @author jaquez
 * @date 2021/06/28 17:38
 **/
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LogInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.woff",
                "/**/*.ttf"
        );
    }

}
