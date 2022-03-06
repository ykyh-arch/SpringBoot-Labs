package cn.iocoder.springboot.lab58.retrofitdemo.controller;

import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.UserServiceRetrofitClient;
import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.request.UserAddRequest;
import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserServiceRetrofitClient userServiceRetrofitClient;

    @GetMapping("/test01")
    public UserResponse test01() {
        return userServiceRetrofitClient.get(1);
    }

    @GetMapping("/test02A")
    public List<UserResponse> test02A() {
        return userServiceRetrofitClient.list("你猜", 1);
    }

    @GetMapping("/test02B")
    public List<UserResponse> test02B() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", "昵称");
        return userServiceRetrofitClient.list(queryMap);
    }

    @GetMapping("/test03")
    public Integer test03() {
        return userServiceRetrofitClient.add(new UserAddRequest()
            .setName("昵称").setGender(1));
    }

}
