package cn.iocoder.springboot.lab55.mapstructdemo.test;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.OrderBO;
import cn.iocoder.springboot.lab55.mapstructdemo.bo.ProductBO;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.OrderConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.ProductConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.MemberDO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.OrderDO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.ProductDO;

import java.util.Arrays;

/**
 * 商品业务对象测试，默认值
 *
 * @author jaquez
 * @date 2021/11/05 15:18
 **/
public class ProductBOTest {

    public static void main(String[] args) {

        ProductDO productDO = new ProductDO().setId(20113099019L).setName("buzhidao");

        // 进行转换
        ProductBO productBO = ProductConvert.INSTANCE.toBO(productDO);
        System.out.println(productBO);
    }
}
