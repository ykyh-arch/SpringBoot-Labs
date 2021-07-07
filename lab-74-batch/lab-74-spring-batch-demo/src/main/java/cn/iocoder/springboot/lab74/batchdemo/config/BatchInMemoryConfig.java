package cn.iocoder.springboot.lab74.batchdemo.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 可选配置
 * 此类是为了Springboot batch的任务管理使用内存模式
 * 加了此项配置后，不会在数据库中创建元数据表，所有的job都是在内存中管理。
 * 程序重启后，任务信息会丢失，复杂的任务场景不建议加此配置，对于不需要严格任务管理的任务来讲比较合适。
 *
 */
//@Component
public class BatchInMemoryConfig extends DefaultBatchConfigurer {
    //重写数据源，什么都不设置。使用内存模式
    @Override
    public void setDataSource(DataSource dataSource) {
    }
}