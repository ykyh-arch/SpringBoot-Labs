package cn.iocoder.springboot.lab01.springsecurity.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取 i18n 资源文件
 *
 * @author Jaquez
 * @date 2021/10/09 16:51
 */
public class MessageUtils
{
    /**
     * 根据消息键和参数 获取消息 委托给 spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object... args)
    {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
