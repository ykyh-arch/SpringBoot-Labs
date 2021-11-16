package cn.iocoder.springcloud.labx23.hystrixdemo.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * HystrixRequestContextFilter 请求上下文过滤器
 *
 * @author Jaquez
 * @date 2021/11/16 10:18
 */
@Component
@WebFilter(urlPatterns = "/")
public class HystrixRequestContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 初始化 HystrixRequestContext
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        // 继续过滤器
        try {
            chain.doFilter(request, response);
        } finally {
            // 销毁 HystrixRequestContext
            context.close();
        }
    }

}
