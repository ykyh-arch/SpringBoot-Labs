package cn.iocoder.springboot.lab81.arthas.controller;

import cn.iocoder.springboot.lab81.arthas.dataobject.UserDO;
import cn.iocoder.springboot.lab81.arthas.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2021/11/24 16:42
 **/
@RestController
@RequestMapping("/demo")
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/echo")
    public String echo() {

        UserDO userDO = userMapper.selectById(10);

        return userDO.toString();
    }
}
