package cn.iocoder.springboot.lab52.orderservice.feign;

import cn.iocoder.springboot.lab52.orderservice.dto.ProductReduceStockDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @FeignClient
public interface ProductServiceFeignClient {

    @PostMapping(value = "/product/reduce-stock",consumes = "application/json")
    Boolean reduceStock(@RequestBody ProductReduceStockDTO productReduceStockDTO);

}
