package cn.iocoder.springboot.lab34.actuatordemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示控制器
 *
 * @author jaquez
 * @date 2021/09/22 14:14
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/visit")
    public String visit() {
        return "Demo 示例";
    }
}
