package cn.iocoder.springboot.lab60.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Soul 测试 dubbo 应用，目前该网关技术已停更，被 Apache ShenYu 所取代，坑啊
 *
 * 测试 dubbo 注册到 soul 网关报错，查看日志显示不支持 POST方式，后续整合 springboot springcloud 都存在这样的问题，可能 Soul 自身存在 bug 问题
 *
 * @author Jaquez
 * @date 2022/01/11 17:40
 */
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }

}
