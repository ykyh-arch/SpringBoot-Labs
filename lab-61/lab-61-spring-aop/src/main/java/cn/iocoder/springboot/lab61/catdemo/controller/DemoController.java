package cn.iocoder.springboot.lab61.catdemo.controller;

import cn.iocoder.springboot.lab61.catdemo.aop.CatAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示示例
 *
 * @author Jaquez
 * @date 2022/01/18 15:35
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hello")
    @CatAnnotation
    public String hello() {
        return "Jj";
    }

}
