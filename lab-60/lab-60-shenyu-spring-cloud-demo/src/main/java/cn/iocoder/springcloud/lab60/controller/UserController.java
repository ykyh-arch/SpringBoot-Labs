package cn.iocoder.springcloud.lab60.controller;

import cn.iocoder.springcloud.lab60.dto.UserCreateDTO;
import org.apache.shenyu.client.springcloud.annotation.ShenyuSpringCloudClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
// @ShenyuSpringCloudClient(path = "/user/**") // 以下方法都会被代理
@ShenyuSpringCloudClient(path = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/get")
    @ShenyuSpringCloudClient(path = "/get", desc = "获得用户详细")
    public String getUser(@RequestParam("id") final Integer id) {
        return "DEMO:" + id;
    }

    @PostMapping("/create")
    @ShenyuSpringCloudClient(path = "/create", desc = "创建用户")
    public Integer createUser(@RequestBody final UserCreateDTO createDTO) {
        logger.info("[createUser][username({}) password({})]", createDTO.getNickname(), createDTO.getGender());
        return 1;
    }

}
