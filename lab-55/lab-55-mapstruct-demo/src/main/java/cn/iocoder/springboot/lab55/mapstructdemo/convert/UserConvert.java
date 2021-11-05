package cn.iocoder.springboot.lab55.mapstructdemo.convert;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.UserBO;
import cn.iocoder.springboot.lab55.mapstructdemo.bo.UserDetailBO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

// 注解映射器
@Mapper
public interface UserConvert {

    // 通过调用 Mappers 的 #getMapper(Class<T> clazz) 方法，获得 MapStruct 帮我们自动生成的 UserConvert 实现类的对象。
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({
            // 属性对应类型不一样
            @Mapping(source = "birthday", target = "birthday",dateFormat = "yyyy-MM-dd")
    })
    UserBO convert(UserDO userDO);

    @Mappings({
            // 属性不一样转换
            @Mapping(source = "id", target = "userId")
    })
    UserDetailBO convertDetail(UserDO userDO);

    @Mappings({
            // 属性对应类型不一样
            @Mapping(source = "birthday", target = "birthday",dateFormat = "yyyy-MM-dd")
    })
    List<UserBO> convertList(List<UserDO> userDO);

}
