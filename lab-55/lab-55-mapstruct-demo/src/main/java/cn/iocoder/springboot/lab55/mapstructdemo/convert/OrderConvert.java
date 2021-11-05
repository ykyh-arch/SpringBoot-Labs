package cn.iocoder.springboot.lab55.mapstructdemo.convert;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.OrderBO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 订单转化器
 *
 * @author jaquez
 * @date 2021/11/04 17:40
 **/
@Mapper(uses = {MemberConvert.class,ProductConvert.class}) // 通过使用uses将子对象的转换器注入进来，然后通过@Mapping设置好属性映射规则即可
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    // 子对象映射
    @Mapping(source = "memberDO",target = "memberBO")
    @Mapping(source = "productDOList",target = "productBOList")
    @Mapping(target = "createTime", expression = "java(new java.util.Date())") // 映射时间
    OrderBO toBO(OrderDO orderDO);

}
