package cn.iocoder.springboot.lab55.mapstructdemo.test;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.ProductBO;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.ProductConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.ProductRoundConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.ProductDO;

import java.math.BigDecimal;

/**
 * 商品业务对象测试，自定义处理业务逻辑
 *
 * @author jaquez
 * @date 2021/11/05 15:18
 **/
public class ProductBORoundTest {

    public static void main(String[] args) {

        ProductDO productDO = new ProductDO().setId(20113099019L).setName("buzhidao")
                .setPrice(new BigDecimal(-1));

        // 进行转换
        ProductBO productBO = ProductRoundConvert.INSTANCE.toBO(productDO);
        System.out.println(productBO);
    }
}
