package cn.iocoder.springboot.lab39.skywalkingdemo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    // 异步执行任务
    @Async
    public void async() {
        System.out.println("异步任务的执行");
    }

}
