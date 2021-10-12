package cn.iocoder.springboot.lab01.springsecurity.utils;

import cn.iocoder.springboot.lab01.springsecurity.utils.core.UUID;

/**
 *
 * ID 生成器工具类
 * @author Jaquez
 * @date 2021/10/09 17:37
 */
public class IdUtils
{
    /**
     * 获取随机 UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的 UUID，去掉了横线
     * 
     * @return 简化的 UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机 UUID，使用性能更好的 ThreadLocalRandom 生成 UUID
     * 
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的 UUID，去掉了横线，使用性能更好的 ThreadLocalRandom 生成 UUID
     * 
     * @return 简化的 UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }
}
