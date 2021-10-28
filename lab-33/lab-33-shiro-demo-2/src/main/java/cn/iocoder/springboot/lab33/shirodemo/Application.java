package cn.iocoder.springboot.lab33.shirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * 实战演示 shiro 权限控制，前后端分离架构，参考自：https://github.com/YunaiV/renren-fast
 * 账户：admin/admin，token 表结构存储
 * 这里的 shiro 凭证是传入的 token，密码 MD5 校验不在 shiro 完成
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