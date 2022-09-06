package cn.iocoder.springboot.lab85.demo.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 简单封装 Jackson，实现 JSON String<-> Java Object 转换的 Mapper.
 * 可以直接使用公共示例 JsonMapper.INSTANCE，也可以使用不同的 builder 函数创建实例，封装不同的输出风格，不要使用 GSON，在对象稍大时非常缓慢.注意: 需要参考本模块的 POM 文件，显式引用 jackson.
 *
 * @author Jaquez
 * @date 2022/09/05 14:16
 */
@Slf4j
public class JsonMapper {

    private ObjectMapper mapper;

    public JsonMapper() {
        this(null);
    }

    public JsonMapper(Include include) {
        mapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        if (include != null) {
            mapper.setSerializationInclusion(include);
        }

        // 设置输入时忽略在 JSON 字符串中存在但 Java 对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 创建只输出非 Null 的属性到 Json 字符串的 Mapper.
     * @return jsonMapper
     */
    public static JsonMapper nonNullMapper() {
        return new JsonMapper(Include.NON_NULL);
    }

    /**
     * Object可以是 POJO，也可以是 Collection 或数组。 如果对象为 Null, 返回 "null". 如果集合为空集合, 返回"[]".
     * @param object pojo对象
     * @return jsonString
     */
    public String toJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }


}
