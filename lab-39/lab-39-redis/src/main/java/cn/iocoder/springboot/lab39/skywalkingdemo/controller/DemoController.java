package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 入口类
 *
 * @author Jaquez
 * @date 2021/12/14 17:06
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/redis")
    public String redis() {
        this.get("demo");
        return "redis";
    }

    public void get(String key) {
        redisTemplate.opsForValue().get(key);
    }

}
