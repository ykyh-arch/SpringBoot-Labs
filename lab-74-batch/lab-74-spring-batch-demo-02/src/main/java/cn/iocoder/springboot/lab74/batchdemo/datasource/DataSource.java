package cn.iocoder.springboot.lab74.batchdemo.datasource;


import cn.iocoder.springboot.lab74.batchdemo.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * 自定义多数据源切换注解
 *
 * @author Jaquez
 * @date 2022/02/27 20:40
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.DEMO;
}
