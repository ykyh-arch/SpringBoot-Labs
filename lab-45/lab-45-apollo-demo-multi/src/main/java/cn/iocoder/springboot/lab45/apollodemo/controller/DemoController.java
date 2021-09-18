package cn.iocoder.springboot.lab45.apollodemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private Environment environment;

    @GetMapping("/test")
    public void test() {
        System.out.println(environment);
    }

    @Value("${test02}")
    private String test02;

    // 经测试能拿到 db 里的配置信息
    @GetMapping("/test02")
    public String test02() {
        return test02;
    }

}
