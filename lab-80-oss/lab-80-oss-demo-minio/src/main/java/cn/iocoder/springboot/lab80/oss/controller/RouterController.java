package cn.iocoder.springboot.lab80.oss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouterController {
    @GetMapping({"/", "/index.html"})
    public String index() {
        return "index";
    }

    @GetMapping({"/upload.html"})
    public String upload() {
        return "upload";
    }
}
