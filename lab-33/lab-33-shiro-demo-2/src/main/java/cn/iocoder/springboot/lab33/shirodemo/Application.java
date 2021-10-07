package cn.iocoder.springboot.lab33.shirodemo;

import cn.iocoder.springboot.lab33.shirodemo.controller.SysMenuController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * 实战演示 shiro 权限控制，前后端分离架构，参考自：https://github.com/YunaiV/renren-fast
 * 账户：admin/admin
 * @author Jaquez
 * @date 2021/10/05 11:19
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.iocoder.springboot.lab33.shirodemo.mapper")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
    }

}
