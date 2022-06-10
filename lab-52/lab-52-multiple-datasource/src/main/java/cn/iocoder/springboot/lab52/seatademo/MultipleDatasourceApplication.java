package cn.iocoder.springboot.lab52.seatademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类，单体项目多数据源事务的控制，可以参考：jta 轻量级的实现方式
 *
 * @author Jaquez
 * @date 2022/06/09 18:00
 */
@SpringBootApplication
public class MultipleDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourceApplication.class, args);
    }

}
