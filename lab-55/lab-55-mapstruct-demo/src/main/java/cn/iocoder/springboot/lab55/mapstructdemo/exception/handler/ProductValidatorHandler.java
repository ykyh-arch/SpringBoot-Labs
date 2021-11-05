package cn.iocoder.springboot.lab55.mapstructdemo.exception.handler;

import cn.iocoder.springboot.lab55.mapstructdemo.exception.ValidatorException;

import java.math.BigDecimal;

/**
 * 商品参数校验，异常处理
 *
 * @author jaquez
 * @date 2021/11/05 16:21
 **/
public class ProductValidatorHandler {

    public BigDecimal validatePrice(BigDecimal price) throws ValidatorException {
        if(price.compareTo(BigDecimal.ZERO)<0){
            throw new ValidatorException("价格不能小于0！");
        }
        return price;
    }
}
