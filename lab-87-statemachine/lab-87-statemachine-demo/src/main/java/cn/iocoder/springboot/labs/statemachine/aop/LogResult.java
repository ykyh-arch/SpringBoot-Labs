package cn.iocoder.springboot.labs.statemachine.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogResult {

    /**
     * 执行的业务 key
     *
     * @return String
     */
    public String key() default "";

}
