package cn.iocoder.springboot.lab52.orderservice.service;

import cn.iocoder.springboot.lab52.orderservice.dto.AccountReduceBalanceDTO;
import cn.iocoder.springboot.lab52.orderservice.dto.ProductReduceStockDTO;
import cn.iocoder.springboot.lab52.orderservice.feign.AccountServiceFeignClient;
import cn.iocoder.springboot.lab52.orderservice.feign.ProductServiceFeignClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import cn.iocoder.springboot.lab52.orderservice.dao.OrderDao;
import cn.iocoder.springboot.lab52.orderservice.entity.OrderDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;

    @Autowired
    private AccountServiceFeignClient accountServiceFeignClient;

    @Override
    @GlobalTransactional // 声明全局事务
    public Integer createOrder(Long userId, Long productId, Integer price) throws Exception {
        Integer amount = 1; // 购买数量，暂时设置为 1。

        logger.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // 扣减库存
        this.reduceStock(productId, amount);

        // 扣减余额
        this.reduceBalance(userId, price * amount);

        // 保存订单
        OrderDO order = new OrderDO().setUserId(userId).setProductId(productId).setPayAmount(amount * price);
        orderDao.saveOrder(order);
        logger.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

    // 扣减库存
    private void reduceStock(Long productId, Integer amount) throws IOException {
        Boolean success = productServiceFeignClient.reduceStock(new ProductReduceStockDTO().setProductId(productId).setAmount(amount));
        if (!success) {
            throw new RuntimeException("扣除库存失败");
        }
    }

    // 扣减余额
    private void reduceBalance(Long userId, Integer price) throws IOException {
        Boolean success = accountServiceFeignClient.reduceStock(new AccountReduceBalanceDTO().setUserId(userId).setPrice(price));
        if (!success) {
            throw new RuntimeException("扣除余额失败");
        }
    }

}
