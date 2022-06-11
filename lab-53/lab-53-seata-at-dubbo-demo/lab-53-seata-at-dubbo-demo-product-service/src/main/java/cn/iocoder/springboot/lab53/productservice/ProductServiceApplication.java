package cn.iocoder.springboot.lab53.productservice;

import io.seata.discovery.registry.nacos.NacosRegistryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author Jaquez
 * @date 2022/06/11 14:47
 */
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
