package cn.iocoder.springboot.lab16.springdatamongodb.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * Mongo配置类
 * 作用：通过在自定义 MappingMongoConverter Bean 对象，避免实体保存到 MongoDB 中时，会多一个 _class 字段，存储实体的全类名。
 * @author Jaquez
 * @date 2021/07/31 19:26
 */
@Configuration
public class MongoDBConfig {

    // 1
//    {
//        "_id": NumberInt("1"),
//            "username": "yudaoyuanma",
//            "password": "buzhidao",
//            "createTime": ISODate("2021-08-01T04:33:35.344Z"),
//            "profile": {
//        "nickname": "芋道源码",
//                "gender": NumberInt("1")
//    },
//        "_class": "cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO"
//    }

    @Bean // 目的，就是为了移除 _class field 。参考博客 https://blog.csdn.net/bigtree_3721/article/details/82787411
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory,
                                                       MongoMappingContext context,
                                                       BeanFactory beanFactory) {
        // 创建 DbRefResolver 对象
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        // 创建 MappingMongoConverter 对象
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        // 设置 conversions 属性
        try {
            // 设置转化器
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {
        }
        // 设置 typeMapper 属性，从而移除 _class field 。
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }

}
