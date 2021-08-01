package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.Application;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.OrderDO;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.ProductDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testInsert() {
        // 创建 ProductDO 对象
        OrderDO orderDO = new OrderDO("苹果13");
        // 插入
        orderRepository.save(orderDO);
        // 打印 ID
        System.out.println(orderDO.getId());
    }

}
