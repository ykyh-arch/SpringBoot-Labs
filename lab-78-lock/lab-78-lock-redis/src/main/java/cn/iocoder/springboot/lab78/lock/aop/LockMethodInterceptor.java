package cn.iocoder.springboot.lab78.lock.aop;

import cn.iocoder.springboot.lab78.lock.annoation.CacheLock;
import cn.iocoder.springboot.lab78.lock.service.ICacheKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 锁方法拦截器，利用 redis 实现分布式锁
 *
 * @author jaquez
 * @date 2021/09/01 10:35
 **/
@Aspect
@Configuration
public class LockMethodInterceptor {

    private final StringRedisTemplate lockRedisTemplate;
    private final ICacheKeyGenerator cacheKeyGenerator;

    @Autowired
    public LockMethodInterceptor(StringRedisTemplate lockRedisTemplate, ICacheKeyGenerator cacheKeyGenerator) {
        this.lockRedisTemplate = lockRedisTemplate;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    @Around("execution(public * *(..)) && @annotation(cn.iocoder.springboot.lab78.lock.annoation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        try {
            // 采用原生 API 来实现分布式锁，如果不存在则设置 key
            final Boolean success = lockRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(lockKey.getBytes(), new byte[0], Expiration.from(lock.expire(), lock.timeUnit()), RedisStringCommands.SetOption.SET_IF_ABSENT));
            if (!success) {
                // 按理来说，应该抛出一个自定义的 CacheLockException 异常
                throw new RuntimeException("请勿重复请求");
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
            // 演示的话需要注释该代码，实际应该放开
            // lockRedisTemplate.delete(lockKey);
        }
    }

}
