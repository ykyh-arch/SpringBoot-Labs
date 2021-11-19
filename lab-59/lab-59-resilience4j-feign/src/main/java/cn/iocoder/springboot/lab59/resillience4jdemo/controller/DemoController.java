package cn.iocoder.springboot.lab59.resillience4jdemo.controller;

import cn.iocoder.springboot.lab59.resillience4jdemo.feign.UserServiceFeignClient;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign 整合 resilience4j 演示示例
 *
 * @author Jaquez
 * @date 2021/11/18 11:16
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/get_user")
    public String getUser(@RequestParam("id") Integer id) {

        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendA");
        RateLimiter rateLimiter = RateLimiter.ofDefaults("backendB");
        // FeignDecorators，When building FeignDecorators, it is important to be wary of this, since the order affects the resulting behavior.
        FeignDecorators decorators = FeignDecorators.builder()
                .withCircuitBreaker(circuitBreaker)
                .withRateLimiter(rateLimiter)
                .withFallbackFactory(MyFallback::new)
                .build();
        UserServiceFeignClient userService = Resilience4jFeign.builder(decorators).target(UserServiceFeignClient.class, "http://127.0.0.1:18080/");
        logger.info("[getUser][准备调用 user-service 获取用户({})详情]", id);
        return userService.getUser("1");
    }

    class MyFallback implements UserServiceFeignClient {

        private Exception cause;

        public MyFallback(Exception cause) {
            this.cause = cause;
        }

        @Override
        public String getUser(String id) {
            if (cause instanceof FeignException) {
                return "Feign Exception";
            } else if(cause instanceof RequestNotPermitted) {
                return "RateLimiter exception";
            }else if(cause instanceof CallNotPermittedException) {
                return "CircuitBreaker exception";
            }else{
                return "Other exception";
            }
        }
    }

}
