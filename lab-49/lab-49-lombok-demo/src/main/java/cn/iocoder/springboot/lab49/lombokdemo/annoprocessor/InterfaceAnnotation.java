package cn.iocoder.springboot.lab49.lombokdemo.annoprocessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 生成接口的注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE) //源代码阶段,编译后的class上就没有这个注解了
public @interface InterfaceAnnotation {
}
