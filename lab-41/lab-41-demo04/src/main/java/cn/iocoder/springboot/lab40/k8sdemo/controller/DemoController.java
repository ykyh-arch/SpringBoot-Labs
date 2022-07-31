package cn.iocoder.springboot.lab40.k8sdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    // 探活接口，给 k8s 确认应用可用，可用于后续流控
    @GetMapping(value = "/hs")
    public String hs() {
        return "OK";
    }

}
