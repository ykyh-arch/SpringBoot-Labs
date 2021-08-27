package cn.iocoder.springboot.lab76.idempotent.interceptor;

import cn.iocoder.springboot.lab76.idempotent.annotation.AutoIdempotent;
import cn.iocoder.springboot.lab76.idempotent.exception.ServiceException;
import cn.iocoder.springboot.lab76.idempotent.service.ITokenService;
import cn.iocoder.springboot.lab76.idempotent.utils.ResultVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 幂等性拦截器
 *
 * @author jaquez
 * @date 2021/08/27 15:57
 **/
@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private ITokenService tokenService;

    /**
     * 预处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //被ApiIdempotment标记的扫描
        AutoIdempotent methodAnnotation = method.getAnnotation(AutoIdempotent.class);
        if (methodAnnotation != null) {
            try {
                // 幂等性校验，校验通过则放行，校验失败则抛出异常，并通过统一异常处理返回友好提示
                return tokenService.checkToken(request);
            }catch (ServiceException ex){
                ResultVo failedResult = ResultVo.getFailedResult(ex.getCode(), ex.getMessage());
                writeReturnJson(response, JSON.toJSONString(failedResult));
                // throw ex;
                return false;
            }
        }
        //必须返回true,否则会被拦截一切请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 返回的json值
     * @param response
     * @param json
     * @throws Exception
     */
    private void writeReturnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
