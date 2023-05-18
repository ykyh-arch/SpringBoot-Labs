package cn.iocoder.springboot.lab54.eventdemo.springeventdemo;

import org.springframework.context.ApplicationEvent;

/**
 * OrderCreateEvent 订单创建成功事件
 *
 **/
public class OrderCreateEvent  extends ApplicationEvent {

    // 订单 id
    private Long orderId;

    /**
     * @param source  事件源
     * @param orderId 订单 id
     */
    public OrderCreateEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
