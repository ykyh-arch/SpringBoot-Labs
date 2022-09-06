package cn.iocoder.springboot.lab85.demo.annotation;

import cn.iocoder.springboot.lab85.demo.core.SensitiveInfoSerialize;
import cn.iocoder.springboot.lab85.demo.strategy.DefaultSensitiveStrategy;
import cn.iocoder.springboot.lab85.demo.strategy.IStrategy;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 脱敏信息
 *
 * @author Jaquez
 * @date 2022/09/05 16:24
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveInfoSerialize.class)
@Inherited
public @interface SensitiveInfo {

    /**
     * 脱敏策略
     * @return
     */
    Class<? extends IStrategy> strategy() default DefaultSensitiveStrategy.class;

    /**
     * 输入格式，使用正则表达式, 优先级大于 value
     *
     * @return 格式
     */
    String pattern() default "";

    /**
     * 替换目标字符, 优先级大于 value
     *
     * @return 替换目标字符串
     */
    String replaceChar() default "";

    /**
     * 开始显示几位
     * @return
     */
    int begin() default 0;

    /**
     * 结束显示几位
     * @return
     */
    int end() default 0;
}
