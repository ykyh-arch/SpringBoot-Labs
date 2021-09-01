package cn.iocoder.springboot.lab78.lock.contoller;

import cn.iocoder.springboot.lab78.lock.annoation.CacheLock;
import cn.iocoder.springboot.lab78.lock.annoation.CacheParam;
import cn.iocoder.springboot.lab78.lock.entity.Test;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

/**
 * 测试控制器
 *
 * @author jaquez
 * @date 2021/09/01 11:49
 **/
@RestController
public class TestController {

    @CacheLock(prefix = "test")
    @GetMapping("/test")
    public String test(@CacheParam(name = "token") @RequestParam String token) {
        return "ok - " + token;
    }

    @CacheLock(prefix = "query")
    @GetMapping("/query")
    public String query(Test test) {
        return JSON.toJSONString(test);
    }

}
