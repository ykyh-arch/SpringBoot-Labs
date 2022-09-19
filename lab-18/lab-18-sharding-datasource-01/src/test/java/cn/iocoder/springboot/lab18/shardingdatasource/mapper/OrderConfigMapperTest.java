package cn.iocoder.springboot.lab18.shardingdatasource.mapper;

import cn.iocoder.springboot.lab18.shardingdatasource.Application;
import cn.iocoder.springboot.lab18.shardingdatasource.dataobject.OrderConfigDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试类
 *
 * @author Jaquez
 * @date 2022/09/19 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderConfigMapperTest {

    @Autowired
    private OrderConfigMapper orderConfigMapper;

    @Test
    public void testSelectById() {
        OrderConfigDO orderConfig = orderConfigMapper.selectById(1);
        System.out.println(orderConfig);
    }

}
