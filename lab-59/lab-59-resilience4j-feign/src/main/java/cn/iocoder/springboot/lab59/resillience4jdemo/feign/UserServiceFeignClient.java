package cn.iocoder.springboot.lab59.resillience4jdemo.feign;

import feign.Param;
import feign.RequestLine;

public interface UserServiceFeignClient {

    @RequestLine("GET /user/get?id={id}")
    String getUser(@Param("id")String id);

}
