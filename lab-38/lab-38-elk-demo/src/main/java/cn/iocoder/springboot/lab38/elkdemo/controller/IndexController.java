package cn.iocoder.springboot.lab38.elkdemo.controller;

import cn.iocoder.springboot.lab38.elkdemo.utils.InputMDC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author jaquez
 * @date 2021/08/23 16:13
 **/
@Slf4j
@RestController
public class IndexController {

    @RequestMapping(value = "/index")
    public String index() {
        InputMDC.putMDC();

        log.info("我是一条info日志");

        log.warn("我是一条warn日志");

        log.error("我是一条error日志");

        return "idx";
    }


    @RequestMapping(value = "/err")
    public String err() {
        InputMDC.putMDC();
        try {
            int a = 1/0;
        } catch (Exception e) {
            log.error("算术异常", e);
        }
        return "err";
    }

}
