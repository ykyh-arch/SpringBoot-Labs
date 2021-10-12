package cn.iocoder.springboot.lab01.springsecurity.exception.module.user;

import cn.iocoder.springboot.lab01.springsecurity.exception.BaseException;

/**
 * 用户信息异常类
 *
 * @author Jaquez
 * @date 2021/10/09 16:41
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
