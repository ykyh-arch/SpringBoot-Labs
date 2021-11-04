package cn.iocoder.springboot.lab80.oss.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常全局处理类
 *
 * @author Jaquez
 * @date 2021/11/04 11:59
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseWrapper notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return ResponseWrapper.error("运行时异常:" + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseWrapper handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return ResponseWrapper.error("服务器错误，请联系管理员");
    }

    @ExceptionHandler(BusinessException.class)
    public Object businessException(HttpServletRequest request, BusinessException e)
    {
        log.error(e.getMessage(), e);
        return ResponseWrapper.error(e.getMessage());
    }

}
