package cn.iocoder.springboot.lab75.activitidemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author OZY
 * @Date 2019-12-19 16:28
 * @Description
 * @Version V1.0
 **/
@Controller
public class IndexController {

    /**
     * 模型管理页面跳转
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "/index";
    }

}
