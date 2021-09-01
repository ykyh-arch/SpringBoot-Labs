package cn.iocoder.springboot.lab78.lock.service;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Key 生成器
 * @author Jaquez
 * @date 2021/09/01 10:15
 */
public interface ICacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
