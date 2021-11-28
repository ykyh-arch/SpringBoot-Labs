package cn.iocoder.springboot.lab46.sentineldemo.web;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类，针对自定义 sentinel-spring-webmvc-adapter 提供的拦截器
 *
 * @author Jaquez
 * @date `2021/11/25` 11:04
 */
@ControllerAdvice(basePackages = "cn.iocoder.springboot.lab46.sentineldemo.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BlockException.class)
    public String blockExceptionHandler(BlockException blockException) {
        return "请求过于频繁";
    }

}
