package cn.iocoder.springboot.lab54.eventdemo.springeventdemo;

import org.springframework.context.ApplicationListener;

/**
 * 订单创建成功给用户发送邮件
 *
 **/
public class SendEmailOnOrderCreateListener implements ApplicationListener<OrderCreateEvent> {

    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        System.out.println(String.format("订单【%d】创建成功，给下单人发送邮件通知!", event.getOrderId()));
    }
}
