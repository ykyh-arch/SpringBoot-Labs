package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.annotation.MultiTransaction;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.entity.Order;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper; // 默认库
    private final StockService stockService; // 在其他库

    // 库存所在数据源 id
    public static final Integer STOCK_DATASOURCE_ID = 10002;

    // 库存 id
    public static final Integer STOCK_ID = 1000;

    public List<Order> list() {
        return orderMapper.selectList(Wrappers.query());
    }


    /**
     * 下单，减库存
     * 这里开启了跨库事务，接着 stockService 会开启新的事务
     * @param order
     */
    @MultiTransaction
    public void save(Order order) {
        orderMapper.insert(order);
        // 指定库存减去 1
        stockService.decreasingStock(STOCK_DATASOURCE_ID,1,STOCK_ID);
        // 手动抛出异常
        // System.out.println(1/0);
    }
}
