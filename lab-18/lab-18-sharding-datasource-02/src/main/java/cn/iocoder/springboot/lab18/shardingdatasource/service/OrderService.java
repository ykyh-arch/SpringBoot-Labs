package cn.iocoder.springboot.lab18.shardingdatasource.service;

import cn.iocoder.springboot.lab18.shardingdatasource.dataobject.OrderDO;
import cn.iocoder.springboot.lab18.shardingdatasource.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public void add(OrderDO order) {
        // 这里先假模假样的读取一下。读取从库
        OrderDO exists = orderMapper.selectById(1);
        System.out.println(exists);

        // 插入订单
        orderMapper.insert(order);

        // 这里先假模假样的读取一下。读取主库，在 Sharding-JDBC 中，读写分离约定：同一线程且同一数据库连接内，如有写入操作，以后的读操作均从主库读取，用于保证数据一致性。
        exists = orderMapper.selectById(1);
        System.out.println(exists);
    }

    public OrderDO findById(Integer id) {
        return orderMapper.selectById(id);
    }

}
