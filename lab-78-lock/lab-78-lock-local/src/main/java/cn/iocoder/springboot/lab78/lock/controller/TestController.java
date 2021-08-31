package cn.iocoder.springboot.lab78.lock.controller;

import cn.iocoder.springboot.lab78.lock.annoation.LocalLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author jaquez
 * @date 2021/08/31 16:33
 **/
@RestController
public class TestController {

    // @LocalLock(key = "test:arg[0]")
    @LocalLock
    @GetMapping("/test")
    public String test(@RequestParam String str) {
        return "ok - " + str;
    }

}
