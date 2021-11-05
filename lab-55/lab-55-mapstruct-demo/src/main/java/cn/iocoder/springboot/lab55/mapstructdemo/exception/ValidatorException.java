package cn.iocoder.springboot.lab55.mapstructdemo.exception;

/**
 * 参数校验异常
 *
 * @author jaquez
 * @date 2021/11/05 16:19
 **/
public class ValidatorException extends Exception{

    public ValidatorException(String message) {
        super(message);
    }
}
