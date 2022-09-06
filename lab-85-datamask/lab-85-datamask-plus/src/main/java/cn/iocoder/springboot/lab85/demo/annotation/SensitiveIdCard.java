package cn.iocoder.springboot.lab85.demo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 身份证号脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 17:36
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveIdCard.class,pattern = "(?<=\\w{0})\\w(?=\\w{4})",replaceChar = "*")
@JacksonAnnotationsInside
public @interface SensitiveIdCard {

}
