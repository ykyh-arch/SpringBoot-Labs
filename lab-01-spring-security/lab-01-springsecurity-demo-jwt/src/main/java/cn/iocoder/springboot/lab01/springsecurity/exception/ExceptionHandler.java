package cn.iocoder.springboot.lab01.springsecurity.exception;

import cn.iocoder.springboot.lab01.springsecurity.utils.CommonResponseBodyWrapper;
import cn.iocoder.springboot.lab01.springsecurity.utils.SysResponseCodeAndMsgEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 *
 * @author jaquez
 * @date 2021/09/26 15:05
 **/
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResponseBodyWrapper businessExceptionHandler(BusinessException e) {
        return CommonResponseBodyWrapper.error(String.valueOf(SysResponseCodeAndMsgEnum.ERROR.getCode()),e.getMessage());
    }
}
