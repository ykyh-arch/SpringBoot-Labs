package cn.iocoder.springboot.lab73.aopdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/test")
    public String echo(String id,String data) {
//        int result = 1/0;
        System.out.println("id : "+id+" ，data : "+data);
        return "success";
    }

}
