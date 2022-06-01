package cn.iocoder.springboot.lab29.asynctask.controller;

import cn.iocoder.springboot.lab29.asynctask.task.OrderTask;
import cn.iocoder.springboot.lab29.asynctask.task.StockTask;
import cn.iocoder.springboot.lab29.asynctask.threads.ThreadPoolManager;
import cn.iocoder.springboot.lab29.asynctask.threads.ThreadPoolManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2022/05/31 16:48
 **/
@RestController
public class TestController {

    @Autowired
    ThreadPoolManager threadPoolManager;

    /**
     * 测试模拟下单请求入口
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {

        OrderTask orderTask = new OrderTask(UUID.randomUUID().toString());
        StockTask stockTask = new StockTask(ThreadLocalRandom.current().nextInt(1,999));
        threadPoolManager.addTask(orderTask);
        ThreadPoolManagerUtil.<StockTask>me().addTask(stockTask);
        // threadPoolManager.addTask(stockTask);

        return "test success";
    }

    /**
     * 停止服务
     * @return
     */
    @GetMapping("/end")
    public String end() {

        threadPoolManager.shutdown();
        Queue q = threadPoolManager.getMsgQueue();
        System.out.println("关闭了线程服务，还有未处理的信息条数：" + q.size());

        return "关闭了线程服务，还有未处理的信息条数：" + q.size();
    }

}
