package cn.iocoder.springboot.lab17.dynamicdatasource.mp.service;

import cn.iocoder.springboot.lab17.dynamicdatasource.mp.dataobject.OrderDO;
import cn.iocoder.springboot.lab17.dynamicdatasource.mp.dataobject.StockDO;
import cn.iocoder.springboot.lab17.dynamicdatasource.mp.mapper.orders.OrderMapper;
import cn.iocoder.springboot.lab17.dynamicdatasource.mp.mapper.stocks.StockMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StockMapper stockMapper;

    @Transactional
    @Override
    public void order() {

        // 先插入 order 库
        OrderDO order = new OrderDO();
        order.setId(1);
        order.setName("Jaquez buy a apple");
        order.setStockId(1);
        orderMapper.insert(order);

        // 抛出异常，模拟事务失效，回滚策略，测试通过
        // int i = 1 / 0;

        // 再更新 stock 库，订单减 1
        StockDO stock = stockMapper.selectById(1);
        stock.setNumber(stock.getNumber()-1);
        stockMapper.updateById(stock);

        // 抛出异常，模拟事务失效，回滚策略
        // int i = 1 / 0;

    }
}
