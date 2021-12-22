package cn.iocoder.springboot.lab39.skywalkingdemo.providerdemo.service;

import cn.iocoder.springboot.lab39.skywalkingdemo.api.UserService;

//服务提供者
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public String get(Integer id) {
        return "user:" + id;
    }

}
