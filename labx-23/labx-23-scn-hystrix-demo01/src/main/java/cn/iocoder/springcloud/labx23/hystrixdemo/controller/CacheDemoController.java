package cn.iocoder.springcloud.labx23.hystrixdemo.controller;

import cn.iocoder.springcloud.labx23.hystrixdemo.service.CacheDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存测试类
 *
 * @author Jaquez
 * @date 2021/11/16 10:39
 */
@RestController
@RequestMapping("/cache-demo")
public class CacheDemoController {

    @Autowired
    private CacheDemoService cacheDemoService;

    @GetMapping("/get_user")
    public String getUser(@RequestParam("id") Integer id) {
        String userA = cacheDemoService.getUser(id);
        String userB = cacheDemoService.getUser(id);
        String userC = cacheDemoService.getUser(id);
        return userC;
    }

    @GetMapping("/update_user")
    public String updateUser(@RequestParam("id") Integer id) {
        String userA = cacheDemoService.getUser(id);
        cacheDemoService.updateUser(id);
        String userC = cacheDemoService.getUser(id);
        return userC;
    }

}
