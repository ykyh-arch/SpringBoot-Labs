package cn.iocoder.springboot.lab27.springwebflux.core.web;

import cn.iocoder.springboot.lab27.springwebflux.constants.ServiceExceptionEnum;
import cn.iocoder.springboot.lab27.springwebflux.core.exception.ServiceException;
import cn.iocoder.springboot.lab27.springwebflux.core.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebInputException;

/**
 * 全局异常处理类
 * 此方法仅支持注解编程模型，不支持函数式编程模型，一般推荐使用注解编程模型
 * 函数式编程方式统一异常处理参考：https://www.baeldung.com/spring-webflux-errors
 * @author Jaquez
 * @date 2021/07/29 11:11
 */
@ControllerAdvice(basePackages = "cn.iocoder.springboot.lab27.springwebflux.controller")
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理 ServiceException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理 ServerWebInputException 异常
     * ServerWebInputException 请求参数异常
     * WebFlux 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = ServerWebInputException.class)
    public CommonResult serverWebInputExceptionHandler(ServerWebInputException ex) {
        logger.debug("[ServerWebInputExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return CommonResult.error(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),
                ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(Exception e) {
        // 记录异常日志
        logger.error("[exceptionHandler]", e);
        // 返回 ERROR CommonResult
        return CommonResult.error(ServiceExceptionEnum.SYS_ERROR.getCode(),
                ServiceExceptionEnum.SYS_ERROR.getMessage());
    }

}
