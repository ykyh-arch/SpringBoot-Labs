package cn.iocoder.springboot.lab01.springsecurity.controller;

import cn.iocoder.springboot.lab01.springsecurity.service.UserService;
import cn.iocoder.springboot.lab01.springsecurity.utils.CommonResponseBodyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2021/09/25 21:38
 **/
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserService userService;


    @GetMapping("/index")
    public CommonResponseBodyWrapper<String> index(){
        return CommonResponseBodyWrapper.success("index");
    }

    @GetMapping("/inner")
    public CommonResponseBodyWrapper<String> inner(){
        return CommonResponseBodyWrapper.success("inner");
    }

    @PostMapping("/login")
    public CommonResponseBodyWrapper<String> login(@RequestParam("username") String username, String password,
                                                   HttpServletRequest request, HttpServletResponse response){

        return CommonResponseBodyWrapper.success(userService.login(username,password,request,response));
    }
}
