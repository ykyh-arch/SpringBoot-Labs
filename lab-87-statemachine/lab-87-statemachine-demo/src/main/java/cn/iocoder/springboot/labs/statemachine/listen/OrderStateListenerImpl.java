package cn.iocoder.springboot.labs.statemachine.listen;

import cn.iocoder.springboot.labs.statemachine.aop.LogResult;
import cn.iocoder.springboot.labs.statemachine.entity.Order;
import cn.iocoder.springboot.labs.statemachine.eunms.CommonConstants;
import cn.iocoder.springboot.labs.statemachine.eunms.OrderStatus;
import cn.iocoder.springboot.labs.statemachine.eunms.OrderStatusChangeEvent;
import cn.iocoder.springboot.labs.statemachine.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OrderStateListenerImpl
 *
 * @author fw001
 * @date 2023/12/13 10:43
 **/
@Slf4j
@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListenerImpl {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;

    // 实现状态的自动转化
    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    @LogResult(key = CommonConstants.payTransition)
    public void payTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("支付，状态机反馈信息：{}",  message.getHeaders().toString());
        // 更新订单
        order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
        orderMapper.updateById(order);
        // 其他业务
        // 模拟异常
//        if(Objects.equals(order.getName(),"订单A")){
//            throw new RuntimeException("执行业务异常");
//        }

    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    @LogResult(key = CommonConstants.deliverTransition)
    public void deliverTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("发货，状态机反馈信息：{}",  message.getHeaders().toString());
        // 更新订单
        order.setStatus(OrderStatus.WAIT_RECEIVE.getKey());
        orderMapper.updateById(order);
        // 其他业务
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    @LogResult(key = CommonConstants.receiveTransition)
    public void receiveTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("确认收货，状态机反馈信息：{}",  message.getHeaders().toString());
        // 更新订单
        order.setStatus(OrderStatus.FINISH.getKey());
        orderMapper.updateById(order);
        // 其他业务
    }

}
