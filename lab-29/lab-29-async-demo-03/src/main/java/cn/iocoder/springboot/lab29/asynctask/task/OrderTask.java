package cn.iocoder.springboot.lab29.asynctask.task;

import cn.iocoder.springboot.lab29.asynctask.threads.CommonThread;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 下单线程类
 *
 * @author jaquez
 * @date 2022/05/31 16:10
 **/
@Component
public class OrderTask extends CommonThread {

    private String orderNumber; // 订单号

    public OrderTask() {
    }

    public OrderTask(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public void run() {

        // 业务操作
        System.out.println("多线程已经处理订单插入系统，订单号：" + orderNumber);

    }

    @Override
    public String getTaskParam() {
        return JSON.toJSONString(this.orderNumber);
    }

}
