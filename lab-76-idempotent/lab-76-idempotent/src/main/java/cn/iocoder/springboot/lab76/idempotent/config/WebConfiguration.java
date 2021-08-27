package cn.iocoder.springboot.lab76.idempotent.config;

import cn.iocoder.springboot.lab76.idempotent.interceptor.AutoIdempotentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * WebMVC 配置
 *
 * @author jaquez
 * @date 2021/08/27 15:50
 **/
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(autoIdempotentInterceptor);
    }
}
