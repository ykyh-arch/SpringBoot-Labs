package cn.iocoder.springcloud.labx23.hystrixdemo.feign;

import cn.iocoder.springcloud.labx23.hystrixdemo.fallback.UserServiceFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign 接口定义
 *
 * @author Jaquez
 * @date 2021/11/16 15:35
 */
@FeignClient(name = "user-service", url = "http://127.0.0.1:18080", fallbackFactory = UserServiceFeignClientFallbackFactory.class) // 申明一个客户端
public interface UserServiceFeignClient {

    @GetMapping("/user/get")
    String getUser(@RequestParam("id") Integer id);

}
