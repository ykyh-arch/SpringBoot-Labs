package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web;

import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.application.Application;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.entity.Order;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.entity.Stock;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service.OrderService;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service.OrderService.STOCK_DATASOURCE_ID;

/**
 * 单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class WebApplicationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockService stockService;

    /**
     *  演示多库：
     *      订单库->库存库->订单库
     */
    @Test
    public void testOrderService() {
        List<Order> orderList = orderService.list();
        Assert.assertTrue(orderList.size() > 0);
        OrderService orderServiceProxy = Application.resolve(OrderService.class);
        // 切库，访问库存库
        List<Stock> stockList = stockService.list(STOCK_DATASOURCE_ID);
        Assert.assertTrue(stockList.size() > 0);
        // 切回主库，访问订单
        orderList = orderServiceProxy.list();
        Assert.assertTrue(orderList.size() > 0);
    }
}
