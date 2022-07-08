package cn.iocoder.springboot.lab77.ratelimiter.anno;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SemaphoreLimit {

    String limitKey() default ""; // 限流的 key

    int value()  default 0;  // 发放的许可证数量
}
