package cn.iocoder.springboot.lab23.springmvc.core.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器配置，
 * 日志拦截器
 * 参考：https://github.com/YunaiV/onemall/blob/74724637b7408461e6570855172c753337293b30/common/mall-spring-boot/src/main/java/cn/iocoder/mall/spring/boot/web/interceptor/AccessLogInterceptor.java
 * @author jaquez
 */
public class FirstInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("[preHandle][handler({})]", handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("[postHandle][handler({})]", handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("[afterCompletion][handler({})]", handler, ex);
    }

}
