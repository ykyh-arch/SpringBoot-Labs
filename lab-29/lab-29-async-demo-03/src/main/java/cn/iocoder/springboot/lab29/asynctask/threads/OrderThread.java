package cn.iocoder.springboot.lab29.asynctask.threads;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 下单线程类
 *
 * @author jaquez
 * @date 2022/05/31 16:10
 **/
@Component
@Scope("prototype") //spring 多例
public class OrderThread extends CommonThread{

    private String orderNumber; // 订单号

    private String taskName = "order-task";

    public OrderThread() {
    }

    public OrderThread(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String getTaskName() {
        return Thread.currentThread().getName() + "-" + taskName + "-" + System.currentTimeMillis();
    }

    @Override
    public void run() {

        // 业务操作
        System.out.println("多线程已经处理订单插入系统，订单号：" + orderNumber);

    }
}
