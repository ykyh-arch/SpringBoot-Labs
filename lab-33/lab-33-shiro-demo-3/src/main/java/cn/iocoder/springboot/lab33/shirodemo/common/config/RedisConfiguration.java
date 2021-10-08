package cn.iocoder.springboot.lab33.shirodemo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 配置类
 * JDK序列化：（标志位 + 字符串长度）（16 进制字符） + 字符串内容
 * String序列化：将对象转化字符串，然后将字符串转化成二进制byte数组文件，反序列化逆操作（ConversionService -》实现对象和String的转换）
 * JSON序列化
 * 对象序列化：serialize（）将对象转化成二进制byte数组文件（不涉及String操作）
 * 对象反序列化：deserialize（）将二进制byte数组文件转化对象
 * XML 序列化方式： 将对象和 String 的转换，从而 String 和二进制数组的转换。
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 设置开启事务支持，在应用程序中处理一个请求时，如果我们的方法开启 Trasaction 功能，Spring 会把数据库的 Connection 连接和当前线程进行绑定，从而实现 Connection 打开一个 Transaction 后，所有当前线程的数据库操作都在该 Connection 上执行，达到所有操作在这个 Transaction 中，最终提交或回滚。
        template.setEnableTransactionSupport(true);

        // 设置 RedisConnection 工厂。😈 它就是实现多种 Java Redis 客户端接入的秘密工厂。感兴趣的胖友，可以自己去撸下。
        template.setConnectionFactory(factory);

        // 使用 String 序列化方式，序列化 KEY 。
        template.setKeySerializer(RedisSerializer.string());

        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
        template.setValueSerializer(RedisSerializer.json());

        return template;
    }

}
