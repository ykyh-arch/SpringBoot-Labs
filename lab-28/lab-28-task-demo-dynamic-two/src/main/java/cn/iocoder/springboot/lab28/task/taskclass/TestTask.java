package cn.iocoder.springboot.lab28.task.taskclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 测试任务类
 *
 * @author jaquez
 * @date 2021/07/08 16:25
 **/
@Component("testTask")
public class TestTask {

    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    public void execute(){
        logger.debug(String.format("这是一个无参定时任务，执行时间：%s", System.currentTimeMillis()));
    }

    public void execute(String str){
        logger.debug(String.format("这是一个有参定时任务，参数为：%s，执行时间：%s", str,System.currentTimeMillis()));
    }

}
