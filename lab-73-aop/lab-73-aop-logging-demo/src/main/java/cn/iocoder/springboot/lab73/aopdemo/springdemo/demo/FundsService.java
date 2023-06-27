package cn.iocoder.springboot.lab73.aopdemo.springdemo.demo;

/**
 * FundsService 模拟资金操作，充值、提现、查询余额
 *
 * @author jaquez
 * @date 2023/06/19 15:10
 **/
public class FundsService {

    // 账户余额
    private double balance = 1000;

    // 模拟充值
    double recharge(String userName, double price) {
        System.out.println(String.format("%s充值%s", userName, price));
        balance += price;
        return balance;
    }

    // 模拟提现
    double cashOut(String userName, double price) {
        if (balance < price) {
            throw new RuntimeException("余额不足!");
        }
        System.out.println(String.format("%s提现%s", userName, price));
        balance -= price;
        return balance;
    }

    // 获取余额
    double getBalance(String userName) {
        return balance;
    }
}
