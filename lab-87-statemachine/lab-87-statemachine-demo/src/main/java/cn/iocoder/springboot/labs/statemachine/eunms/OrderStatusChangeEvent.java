package cn.iocoder.springboot.labs.statemachine.eunms;

public enum OrderStatusChangeEvent {

    // 支付，发货，确认收货
    PAYED, DELIVERY, RECEIVED;
}
