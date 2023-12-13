package cn.iocoder.springboot.labs.statemachine.service;

import cn.iocoder.springboot.labs.statemachine.entity.Order;

public interface OrderService {
    Order getById(Long id);

    Order create(Order order);

    Order pay(Long id);

    Order deliver(Long id);

    Order receive(Long id);
}
