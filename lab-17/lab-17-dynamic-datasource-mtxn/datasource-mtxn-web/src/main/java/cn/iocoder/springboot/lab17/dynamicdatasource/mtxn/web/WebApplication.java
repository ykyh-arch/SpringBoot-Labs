package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类，项目参考自：https://blog.csdn.net/qq381332153/article/details/126541276
 *
 */
@SpringBootApplication
@MapperScan({"cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.mapper","cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.mapper"})
@ComponentScan({"cn.iocoder.springboot.lab17.dynamicdatasource.mtxn"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	

}
