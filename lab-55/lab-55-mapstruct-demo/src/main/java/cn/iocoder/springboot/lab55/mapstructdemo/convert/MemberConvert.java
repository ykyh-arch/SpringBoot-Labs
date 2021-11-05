package cn.iocoder.springboot.lab55.mapstructdemo.convert;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.MemberBO;
import cn.iocoder.springboot.lab55.mapstructdemo.bo.MemberOrderBO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.MemberDO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MemberConvert {

    MemberConvert INSTANCE = Mappers.getMapper(MemberConvert.class);

    @Mapping(source = "phone",target = "phoneNumber") // 字段不一样
    @Mapping(target = "birthday",dateFormat = "yyyy-MM-dd",expression = "java(new java.text.SimpleDateFormat(\"yyyy-MM-dd\").format(new java.util.Date()) )") // 类型不一样
    MemberBO toBO(MemberDO memberDO);

    // 集合映射
    @Mapping(source = "phone",target = "phoneNumber")
    @Mapping(source = "birthday",target = "birthday",dateFormat = "yyyy-MM-dd")
    List<MemberBO> toBOList(List<MemberDO> list);

    // 通过参数名称.属性的名称来指定
    @Mapping(source = "memberDO.phone",target = "phoneNumber") // 字段不通
    @Mapping(source = "memberDO.birthday",target = "birthday",dateFormat = "yyyy-MM-dd") // 类型不通
    @Mapping(source = "memberDO.id",target = "id")
    @Mapping(source = "orderDO.orderSn", target = "orderSn")
    @Mapping(source = "orderDO.receiverAddress", target = "receiverAddress")
    MemberOrderBO toMemberOrderBO(MemberDO memberDO, OrderDO orderDO);

}
