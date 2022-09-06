package cn.iocoder.springboot.lab85.demo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 地址脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 17:08
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveAddress.class, pattern = "(.{5}).+(.{4})",replaceChar = "$1*****$2")
@JacksonAnnotationsInside
public @interface SensitiveAddress {

}
