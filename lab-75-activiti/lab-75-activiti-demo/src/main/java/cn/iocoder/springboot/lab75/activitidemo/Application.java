package cn.iocoder.springboot.lab75.activitidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考：https://blog.csdn.net/wamr_o/article/details/103628022
 * https://blog.csdn.net/lyf_ldh/article/details/100595558
 * @author Jaquez
 * @date 2021/07/08 18:35
 */
//屏蔽冲突的包
@SpringBootApplication(exclude={
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
