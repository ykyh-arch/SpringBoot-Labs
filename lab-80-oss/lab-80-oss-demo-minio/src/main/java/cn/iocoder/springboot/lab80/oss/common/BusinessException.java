package cn.iocoder.springboot.lab80.oss.common;

/**
 * 业务异常
 *
 * @author jaquez
 * @date 2021/11/04 11:55
 **/
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message)
    {
        this.message = message;
    }

    public BusinessException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

}
