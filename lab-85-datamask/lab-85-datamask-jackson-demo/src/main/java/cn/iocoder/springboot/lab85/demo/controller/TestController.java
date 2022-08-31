package cn.iocoder.springboot.lab85.demo.controller;

import cn.iocoder.springboot.lab85.demo.dataobject.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jaquez
 * @date 2022/08/31 16:18
 **/
@RestController
public class TestController {

    @GetMapping("test")
    public UserInfo test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1004L);
        userInfo.setName("张无忌");
        userInfo.setEmail("1859656863@qq.com");
        userInfo.setPhone("15286535426");
        userInfo.setAddr("明教光明顶");
        return userInfo;
    }

}
