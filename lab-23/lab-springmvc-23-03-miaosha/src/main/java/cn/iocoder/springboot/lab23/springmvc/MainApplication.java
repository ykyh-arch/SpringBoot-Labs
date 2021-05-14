package cn.iocoder.springboot.lab23.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 秒杀案例，源码参考：https://github.com/csy512889371/learndemo
 * 		   博客参考：https://blog.csdn.net/qq_27384769/article/details/79992427
 * 接口防刷+限流
 * @author Jaquez
 * @date 2021/05/14 11:13
 */
@SpringBootApplication
public class MainApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
}
