package cn.iocoder.springboot.labs.statemachine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring statemachine 状态机的使用示例，参考：https://mp.weixin.qq.com/s/Ol9_NX1EvO6Ge4HFtR4W-w
 *
 * @author Jaquez
 * @date 2023/02/28 15:27
 */
@EnableTransactionManagement
@MapperScan(basePackages = "cn.iocoder.springboot.labs.statemachine.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
