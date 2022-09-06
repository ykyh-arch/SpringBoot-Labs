package cn.iocoder.springboot.lab85.demo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 银行卡号脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 17:27
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveBankCard.class, pattern = "(?<=\\w{4})\\w(?=\\w{4})",replaceChar = "*")
@JacksonAnnotationsInside // 组合注解
public @interface SensitiveBankCard {

}
