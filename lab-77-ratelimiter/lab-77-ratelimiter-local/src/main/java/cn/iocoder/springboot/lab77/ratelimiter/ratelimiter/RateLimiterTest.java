package cn.iocoder.springboot.lab77.ratelimiter.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RateLimiterTest
 *
 * @author jaquez
 * @date 2022/07/08 10:37
 **/
public class RateLimiterTest {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long begin = System.currentTimeMillis();
        // 每秒允许发放 1 个令牌
        double permits = 1.0;
        RateLimiter limiter = RateLimiter.create(permits);
        for (int i = 1; i <= 10; i++) {
            // 获取 i 个令牌, 当 i 超过 permits 会被阻塞
            double waitTime = limiter.acquire(i);
            System.out.println("curTime=" + sdf.format(new Date()) + " call index:" + i + " waitTime:" + waitTime);
        }
        long end =  System.currentTimeMillis();
        System.out.println("begin time:" + sdf.format(new Date(begin))+",end time:"+sdf.format(new Date(end))+",Total task time："+(end-begin));
    }

}
