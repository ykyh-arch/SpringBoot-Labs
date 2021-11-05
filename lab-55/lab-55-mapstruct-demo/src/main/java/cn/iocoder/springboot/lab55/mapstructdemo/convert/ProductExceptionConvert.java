package cn.iocoder.springboot.lab55.mapstructdemo.convert;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.ProductBO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.ProductDO;
import cn.iocoder.springboot.lab55.mapstructdemo.exception.ValidatorException;
import cn.iocoder.springboot.lab55.mapstructdemo.exception.handler.ProductValidatorHandler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

/**
 * 商品异常映射类
 *
 * @author jaquez
 * @date 2021/11/05 16:26
 **/
@Mapper(uses = {ProductValidatorHandler.class},imports = {UUID.class})
public interface  ProductExceptionConvert {

    ProductExceptionConvert INSTANCE = Mappers.getMapper(ProductExceptionConvert.class);

    @Mapping(target = "id",constant = "-1L")
    @Mapping(source = "count",target = "count",defaultValue = "1")
    @Mapping(target = "productSn",expression = "java(UUID.randomUUID().toString())")
    ProductBO toBO(ProductDO productDO) throws ValidatorException;
}
