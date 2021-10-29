package cn.iocoder.springboot.lab04.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 基于 ADMQ 协议事务机制实现的消息到 broker，事务多了四个步骤：Tx.Select、Tx.Select-Ok、Tx.Commit、Tx.Commit-OK
 * 如果出现异常会走这一步：TxRollback、TxRollback-Ok
 * @author Jaquez
 * @date 2021/10/28 17:53
 */
@SpringBootApplication
@EnableTransactionManagement // 开启事务
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
