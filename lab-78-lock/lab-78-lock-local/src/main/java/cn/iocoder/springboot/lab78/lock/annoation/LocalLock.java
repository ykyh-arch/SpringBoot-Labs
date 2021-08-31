package cn.iocoder.springboot.lab78.lock.annoation;

import java.lang.annotation.*;

/**
 * 本地锁
 * @author Jaquez
 * @date 2021/08/31 16:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {

    /**
     * key
     */
    String key() default "";

    /**
     * 过期时间
     *
     */
    int expire() default 5;

}
