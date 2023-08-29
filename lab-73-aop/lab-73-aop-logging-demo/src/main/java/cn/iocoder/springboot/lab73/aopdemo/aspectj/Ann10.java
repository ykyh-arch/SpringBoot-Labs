package cn.iocoder.springboot.lab73.aopdemo.aspectj;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Ann10 {
}
