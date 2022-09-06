package cn.iocoder.springboot.lab85.demo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 中文姓名脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 17:33
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveChineseName.class, pattern = "(?<=.{1}).",replaceChar = "*")
@JacksonAnnotationsInside
public @interface SensitiveChineseName {

}
