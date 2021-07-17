package cn.iocoder.springboot.lab47.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2021/07/17 15:55
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(String param){

        return "Success";

    }
}
