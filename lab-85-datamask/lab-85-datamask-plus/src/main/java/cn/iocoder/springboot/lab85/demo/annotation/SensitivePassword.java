package cn.iocoder.springboot.lab85.demo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 密码脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 17:49
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitivePassword.class,pattern = "(?<=).",replaceChar = "*")
@JacksonAnnotationsInside
public @interface SensitivePassword {

}
