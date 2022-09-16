package cn.iocoder.springboot.lab86.demo;

import cn.iocoder.springboot.lab86.demo.service.DisruptorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * DemoApplicationTests
 *
 * @author jaquez
 * @date 2022/09/16 14:45
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTest {

    @Autowired
    private DisruptorService disruptorService;

    /**
     * 项目内部使用 Disruptor 做消息队列
     * @throws Exception
     */
    @Test
    public void sayHelloMqTest() throws Exception{
        disruptorService.send("这是一条测试的内容!");
        log.info("数据已发送完毕");
        // 这里停止2000ms是为了确定是处理消息是异步的
        Thread.sleep(2000);
    }

}
