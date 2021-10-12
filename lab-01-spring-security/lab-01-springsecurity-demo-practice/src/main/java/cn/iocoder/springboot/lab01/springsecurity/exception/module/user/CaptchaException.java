package cn.iocoder.springboot.lab01.springsecurity.exception.module.user;

/**
 * 验证码异常类
 *
 * @author Jaquez
 * @date 2021/10/09 16:40
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
