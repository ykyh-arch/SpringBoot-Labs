package cn.iocoder.springboot.lab28.task.taskclass;

import org.springframework.stereotype.Component;

/**
 * 测试任务类
 *
 * @author jaquez
 * @date 2021/07/08 16:25
 **/
@Component
public class TestTask {

    public void execute(){
        System.out.println("这是一个定时任务，执行时间："+ System.currentTimeMillis());
    }

}
