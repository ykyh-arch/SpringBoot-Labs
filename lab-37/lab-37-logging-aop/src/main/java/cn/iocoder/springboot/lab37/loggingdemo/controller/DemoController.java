package cn.iocoder.springboot.lab37.loggingdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 记录访问日志控制器示例
 *
 * @author Jaquez
 * @date 2021/11/22 15:06
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/debug")
    public void debug() {
        logger.debug("debug");
    }

    @GetMapping("/info")
    public void info() {
        logger.info("info");
    }

    @GetMapping("/error")
    public void error() {
        logger.error("error");
    }

}
