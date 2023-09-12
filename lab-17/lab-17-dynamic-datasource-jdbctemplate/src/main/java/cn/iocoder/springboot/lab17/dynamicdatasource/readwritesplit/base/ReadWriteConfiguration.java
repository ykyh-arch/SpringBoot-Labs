package cn.iocoder.springboot.lab17.dynamicdatasource.readwritesplit.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ReadWriteConfiguration
 *
 * @author fw001
 * @date 2023/09/12 15:53
 **/
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true, order = Integer.MAX_VALUE - 1)
@ComponentScan(basePackageClasses = IService.class)
public class ReadWriteConfiguration {

}
