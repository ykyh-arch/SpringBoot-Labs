package cn.iocoder.springboot.lab17.dynamicdatasource.mp;

import cn.iocoder.springboot.lab17.dynamicdatasource.mp.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 程序测试类，参考自：http://www.manongjc.com/detail/28-ddjabcqnbfmofta.html
 *
 * @author jaquez
 * @date 2022/05/30 11:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    protected OrderService orderService;


    @Test
    public void test() {

        orderService.order();
    }


}
