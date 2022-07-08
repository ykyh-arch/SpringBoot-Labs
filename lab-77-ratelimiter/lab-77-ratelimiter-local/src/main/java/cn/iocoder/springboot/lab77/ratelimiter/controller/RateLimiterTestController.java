package cn.iocoder.springboot.lab77.ratelimiter.controller;

import cn.iocoder.springboot.lab77.ratelimiter.anno.RateLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * RateLimiterTestController
 *
 * @author jaquez
 * @date 2022/07/08 11:01
 **/
@RestController
public class RateLimiterTestController {

    @RateLimit
    @RequestMapping(value = "/ratelimit",method = RequestMethod.GET)
    public String ratelimit() throws Exception{
        // 假设业务处理了 1 秒
        TimeUnit.SECONDS.sleep(1);
        return "success";
    }

}
