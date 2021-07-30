package cn.iocoder.springboot.lab27.springwebflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 测试 WebFlux 整合Servlet、Filter、Lister 组件，经测试无效！
 * @author Jaquez
 * @date 2021/07/30 15:59
 */
@SpringBootApplication
@ServletComponentScan // 开启Servlet组件扫描
public class Application {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 注册Servlet
    @Bean
    public ServletRegistrationBean testServlet01() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new HttpServlet() {

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                logger.info("[doGet][uri: {}]", req.getRequestURI());
            }

        });
        servletRegistrationBean.setUrlMappings(Collections.singleton("/test/01"));
        return servletRegistrationBean;
    }

    // 注册Filter
    @Bean
    public FilterRegistrationBean testFilter01() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new Filter() {

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                logger.info("[doFilter]");
                filterChain.doFilter(servletRequest, servletResponse);
            }

        });
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/test/*"));
        return filterRegistrationBean;
    }

    // 注册Listener
    @Bean
    public ServletListenerRegistrationBean<?> testListener01() {
        return new ServletListenerRegistrationBean<>(new ServletContextListener() {

            // 环境初始化
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("[contextInitialized]");
            }

            // 环境销毁
            @Override
            public void contextDestroyed(ServletContextEvent sce) {

            }

        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
