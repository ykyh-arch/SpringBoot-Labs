package cn.iocoder.springboot.lab52.orderservice.feign;

import cn.iocoder.springboot.lab52.orderservice.dto.AccountReduceBalanceDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @FeignClient(value = "accountServiceFeign",url="http://127.0.0.1:8083")
public interface AccountServiceFeignClient {
    
    @PostMapping(value = "/account/reduce-balance",consumes = "application/json")
    Boolean reduceStock(@RequestBody AccountReduceBalanceDTO accountReduceBalanceDTO);

}
