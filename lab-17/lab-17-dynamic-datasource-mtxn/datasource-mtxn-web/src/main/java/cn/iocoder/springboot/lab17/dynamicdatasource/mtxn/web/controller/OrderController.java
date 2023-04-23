package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.controller;

import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.entity.Order;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.entity.Stock;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service.OrderService;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service.OrderService.STOCK_DATASOURCE_ID;
import static cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service.OrderService.STOCK_ID;

@RestController
@RequestMapping(value = "/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StockService stockService;

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public List<Order> list() {
        List<Order> orders = orderService.list();
        return orders;
    }

    /**
     * 下订单
     */
    @GetMapping("/makeOrder")
    public String save() {
        orderService.save(Order.builder().name("苹果手机的订单").orderNo(UUID.randomUUID().toString()).createTime(new Timestamp(System.currentTimeMillis())).build());
        return "下单成功！";
    }

    /**
     * 初始化库存
     * 库存 id 写死。 STOCK_ID
     * @return
     */
    @GetMapping("/init")
    public Stock init() {
        return stockService.save(STOCK_DATASOURCE_ID,
                Stock.builder().id(STOCK_ID).name("图书库存").amount(100L).lastModifyTime(new Timestamp(System.currentTimeMillis())).build());
    }
}
