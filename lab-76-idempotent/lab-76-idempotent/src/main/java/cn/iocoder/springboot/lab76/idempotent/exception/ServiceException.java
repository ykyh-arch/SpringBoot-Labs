package cn.iocoder.springboot.lab76.idempotent.exception;

/**
 * 服务异常
 *
 * @author jaquez
 * @date 2021/08/27 15:26
 **/
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;
    private Integer code;
    public ServiceException(String message, Integer code) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
