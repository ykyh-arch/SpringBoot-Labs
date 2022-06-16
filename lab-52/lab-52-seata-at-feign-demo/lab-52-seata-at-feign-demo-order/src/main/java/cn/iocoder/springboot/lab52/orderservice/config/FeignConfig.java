package cn.iocoder.springboot.lab52.orderservice.config;

import cn.iocoder.springboot.lab52.orderservice.feign.AccountServiceFeignClient;
import cn.iocoder.springboot.lab52.orderservice.feign.ProductServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignConfig
 *
 * @author jaquez
 * @date 2022/06/15 14:34
 **/
@Configuration
public class FeignConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ProductServiceFeignClient productServiceFeignClient() {

        FeignClientBuilder feignClientBuilder = new FeignClientBuilder(this.applicationContext);;
        ProductServiceFeignClient productServiceFeign = feignClientBuilder.forType(ProductServiceFeignClient.class, "productServiceFeign")
                .url("http://127.0.0.1:8082").build();

        return productServiceFeign;
    }

    @Bean
    public AccountServiceFeignClient accountServiceFeignClient() {
        FeignClientBuilder feignClientBuilder = new FeignClientBuilder(this.applicationContext);;
        AccountServiceFeignClient accountServiceFeign = feignClientBuilder.forType(AccountServiceFeignClient.class, "accountServiceFeign")
                .url("http://127.0.0.1:8083").build();

        return accountServiceFeign;
    }

}
