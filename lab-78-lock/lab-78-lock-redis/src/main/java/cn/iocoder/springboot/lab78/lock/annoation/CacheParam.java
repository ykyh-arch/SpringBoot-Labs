package cn.iocoder.springboot.lab78.lock.annoation;

import java.lang.annotation.*;

/**
 * 锁参数
 * @author Jaquez
 * @date 2021/09/01 10:12
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
