package cn.iocoder.springboot.lab77.ratelimiter.controller;

import cn.iocoder.springboot.lab77.ratelimiter.anno.SemaphoreLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * SemaphoreTestController
 *
 * @author jaquez
 * @date 2022/07/08 16:13
 **/
@RestController
public class SemaphoreTestController {

    /**
     * 设置 limitKey = SemaphoreKey，并且许可证只有 3 个
     */
    @SemaphoreLimit(limitKey ="SemaphoreKey", value =3)
    @RequestMapping("/semaphoreLimit")
    public String semaphoreLimit() throws Exception{
        // 假设业务处理了1秒
        TimeUnit.SECONDS.sleep(1);
        return "success";
    }

}
