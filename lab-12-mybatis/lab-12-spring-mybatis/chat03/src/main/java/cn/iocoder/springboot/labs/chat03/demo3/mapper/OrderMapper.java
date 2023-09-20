package cn.iocoder.springboot.labs.chat03.demo3.mapper;

import cn.iocoder.springboot.labs.chat03.demo3.model.OrderModel;

import java.util.List;

public interface OrderMapper {

    List<OrderModel> getList();
}
