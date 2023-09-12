package cn.iocoder.springboot.lab17.dynamicdatasource.readwritesplit.base;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ReadWriteConfiguration.class)
public @interface EnableReadWrite {
}
