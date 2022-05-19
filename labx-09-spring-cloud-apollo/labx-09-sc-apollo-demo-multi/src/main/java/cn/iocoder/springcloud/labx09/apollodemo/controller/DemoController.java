package cn.iocoder.springcloud.labx09.apollodemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value(value = "${test01}")
    private String test01;

    /**
     * 测试 @Value 注解的属性
     */
    @GetMapping("/test")
    public String test() {
        return test01;
    }

}
