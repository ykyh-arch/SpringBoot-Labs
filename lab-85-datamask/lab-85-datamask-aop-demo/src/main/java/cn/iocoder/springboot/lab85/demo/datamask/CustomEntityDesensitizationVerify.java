package cn.iocoder.springboot.lab85.demo.datamask;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义实体数据脱敏
 *
 * @author Jaquez
 * @date 2022/09/01 10:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CustomEntityDesensitizationVerify {
}
