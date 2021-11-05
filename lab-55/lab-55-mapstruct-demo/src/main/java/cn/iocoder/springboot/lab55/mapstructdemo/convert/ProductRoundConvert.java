package cn.iocoder.springboot.lab55.mapstructdemo.convert;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.ProductBO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.ProductDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * 自定义处理商品对象映射
 *
 * @author jaquez
 * @date 2021/11/05 15:27
 **/
@Mapper(imports = {UUID.class})
public abstract class ProductRoundConvert {

    public static ProductRoundConvert INSTANCE = Mappers.getMapper(ProductRoundConvert.class);

    @Mapping(target = "id",constant = "-1L")
    @Mapping(source = "count",target = "count",defaultValue = "1")
    @Mapping(target = "productSn",expression = "java(UUID.randomUUID().toString())")
    public abstract ProductBO toBO(ProductDO productDO);

    // 映射前需要处理的事情
    @BeforeMapping
    public void beforeMapping(ProductDO productDO){
        // 映射前当price<0时设置为0
        if(productDO.getPrice().compareTo(BigDecimal.ZERO)<0){
            productDO.setPrice(BigDecimal.ZERO);
        }
    }

    // 映射后需要处理的事情
    @AfterMapping
    public void afterMapping(@MappingTarget ProductBO productBO){
        //映射后设置当前时间为 createTime
        productBO.setCreateTime(new Date());
    }
}
