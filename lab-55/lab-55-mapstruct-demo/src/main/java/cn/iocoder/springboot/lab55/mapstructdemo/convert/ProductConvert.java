package cn.iocoder.springboot.lab55.mapstructdemo.convert;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.ProductBO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.ProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

/**
 * 通过 @Mapping 注解中的 constant、defaultValue、expression 设置好映射规则；
 * @author Jaquez
 * @date 2021/11/05 15:10
 */
@Mapper(imports = {UUID.class})
public interface ProductConvert {

    ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);

    @Mapping(target = "id",constant = "-1L")
    @Mapping(source = "count",target = "count",defaultValue = "1")
    @Mapping(target = "productSn",expression = "java(UUID.randomUUID().toString())")
    ProductBO toBO(ProductDO productDO);

}
