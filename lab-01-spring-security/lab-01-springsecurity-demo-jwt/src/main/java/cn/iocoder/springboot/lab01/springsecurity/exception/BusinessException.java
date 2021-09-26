package cn.iocoder.springboot.lab01.springsecurity.exception;

import cn.iocoder.springboot.lab01.springsecurity.utils.SysResponseCodeAndMsgEnum;

import java.io.Serializable;

/**
 * 业务异常类
 *
 * @author jaquez
 * @date 2021/09/26 14:45
 **/
public class BusinessException extends RuntimeException implements Serializable {

    private String errorCode;

    public BusinessException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BusinessException(String msg) {
        super(msg);
        this.errorCode = String.valueOf(SysResponseCodeAndMsgEnum.ERROR.getCode());
    }

}
