package cn.iocoder.springboot.lab77.ratelimiter.controller;

import cn.iocoder.springboot.lab77.ratelimiter.annoation.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流控制器
 *
 * @author jaquez
 * @date 2021/08/31 14:19
 **/
@RestController
public class LimiterController {

    // 记录访问次数
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    @Limit(key = "testLimiter", period = 60, count = 10, prefix = "rate")
    @GetMapping("/testLimiter")
    public int testLimiter() {
        // 意味著 60S 内最多允許訪問10次
        return ATOMIC_INTEGER.incrementAndGet();
    }

}
