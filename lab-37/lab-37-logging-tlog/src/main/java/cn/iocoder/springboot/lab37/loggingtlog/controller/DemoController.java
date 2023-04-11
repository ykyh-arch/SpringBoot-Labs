package cn.iocoder.springboot.lab37.loggingtlog.controller;

import com.yomahub.tlog.core.annotation.TLogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/info")
    public void info() {
        logger.info("手动打印输出日志信息");
    }

    @TLogAspect(value = {"param1","param2"},joint = "_",pattern = "<{}>")
    @GetMapping("/tag")
    public void testTag(String param1,String param2) {
        logger.info("手动添加业务标签，可以记录业务参数");
        // 一般线程
        new Thread(()->{
            logger.info("手动添加线程测试一下效果");
        }).start();
        // 线程池
        threadPoolExecutor.execute(()->{
            logger.info("手动添加线程池测试一下效果");
        });
    }

}
