package cn.iocoder.springboot.lab12.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MP 使用参考：https://mp.weixin.qq.com/s/n83IAXMDcgGWN9pDfTWlHA
 * MPJ(MyBatis Plus Join) 使用参考：https://mp.weixin.qq.com/s/4OUsJYLcscmBo2VJJOL2sA
 * @author Jaquez
 * @date 2023/02/28 15:27
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.iocoder.springboot.lab12.mybatis.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
