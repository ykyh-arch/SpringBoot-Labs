package cn.iocoder.springboot.lab46.sentineldemo.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebTotalInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcTotalConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

/**
 * SpringMvcConfiguration 配置类
 *
 * @author Jaquez
 * @date 2021/11/25 10:05
 */
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Add Sentinel interceptor
//        addSentinelWebTotalInterceptor(registry);
        addSentinelWebInterceptor(registry);
    }
    // SentinelWebTotalInterceptor 针对每个 URL 进行流量控制。
    private void addSentinelWebInterceptor(InterceptorRegistry registry) {
        // 创建 SentinelWebMvcConfig 对象
        SentinelWebMvcConfig config = new SentinelWebMvcConfig();
        // 将 URL + Method 作为一个资源，进行流量控制。
        config.setHttpMethodSpecify(true); // 是否包含请求方法。即基于 URL 创建的资源，是否包含 Method。
        // config.setBlockExceptionHandler(new DefaultBlockExceptionHandler()); // 设置 BlockException 处理器。这里交给Spring MVC 全局异常处理类
        config.setOriginParser(new RequestOriginParser() { // 设置请求来源解析器。用于黑白名单控制功能。

            @Override
            public String parseOrigin(HttpServletRequest request) {
                // 从 Header 中，获得请求来源
                String origin = request.getHeader("s-user");
                // 如果为空，给一个默认的
                if (StringUtils.isEmpty(origin)) {
                    origin = "default";
                }
                return origin;
            }

        });

        // 添加 SentinelWebInterceptor 拦截器
        registry.addInterceptor(new SentinelWebInterceptor(config)).addPathPatterns("/**");
    }

    // 针对全局 URL 进行流量控制。简单来说，所有 URL 合计流量，全局统一进行控制。
    private void addSentinelWebTotalInterceptor(InterceptorRegistry registry) {
        // 创建 SentinelWebMvcTotalConfig 对象
        SentinelWebMvcTotalConfig config = new SentinelWebMvcTotalConfig();

        // 添加 SentinelWebTotalInterceptor 拦截器
        registry.addInterceptor(new SentinelWebTotalInterceptor(config)).addPathPatterns("/**");
    }

}
