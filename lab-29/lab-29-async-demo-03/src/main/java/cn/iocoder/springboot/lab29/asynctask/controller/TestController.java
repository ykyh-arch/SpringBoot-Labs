package cn.iocoder.springboot.lab29.asynctask.controller;

import cn.iocoder.springboot.lab29.asynctask.threads.OrderThread;
import cn.iocoder.springboot.lab29.asynctask.threads.ThreadPoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;
import java.util.UUID;

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
    @GetMapping("/start")
    public String start() {

        // 模拟订单号
        String orderNo = System.currentTimeMillis() + UUID.randomUUID().toString();
        OrderThread task = new OrderThread(orderNo);

        threadPoolManager.addTask(task);

        return "Test ThreadPoolExecutor start";
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

        return "Test ThreadPoolExecutor start";
    }

}
