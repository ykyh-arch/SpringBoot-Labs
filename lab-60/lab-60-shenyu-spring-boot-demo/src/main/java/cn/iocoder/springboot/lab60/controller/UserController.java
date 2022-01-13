package cn.iocoder.springboot.lab60.controller;

import cn.iocoder.springboot.lab60.common.CustomShenyuResult;
import cn.iocoder.springboot.lab60.dto.UserCreateDTO;
import org.apache.shenyu.client.springmvc.annotation.ShenyuSpringMvcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
// @ShenyuSpringMvcClient(path = "/user/**")   // 以下所有接口都会被代理
@ShenyuSpringMvcClient(path = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/get")
    @ShenyuSpringMvcClient(path = "/get", desc = "获得用户详细")
    public String getUser(@RequestParam("id") Integer id) {
        return "DEMO:" + id;
    }

    @GetMapping("/getinfo")
    @ShenyuSpringMvcClient(path = "/getinfo", desc = "获得用户详细")
    public CustomShenyuResult getUserInfo(@RequestParam("id") Integer id) {
        return CustomShenyuResult.success("DEMO:" + id);
    }

    @PostMapping("/create")
    @ShenyuSpringMvcClient(path = "/create", desc = "创建用户")
    public Integer createUser(@RequestBody UserCreateDTO createDTO) {
        logger.info("[createUser][username({}) password({})]", createDTO.getNickname(), createDTO.getGender());
        return 1;
    }

}
