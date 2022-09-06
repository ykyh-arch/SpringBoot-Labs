package cn.iocoder.springboot.lab85.demo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 手机号脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 17:47
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveMobile.class,pattern = "(?<=\\w{3})\\w(?=\\w{4})",replaceChar = "*")
@JacksonAnnotationsInside
public @interface SensitiveMobile {

}
