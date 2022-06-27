package cn.iocoder.springboot.lab40.jenkinsdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot应用的后台运行配置参考：https://blog.didispace.com/spring-boot-run-backend/
 *
 * @author Jaquez
 * @date 2022/06/27 10:09
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

}
