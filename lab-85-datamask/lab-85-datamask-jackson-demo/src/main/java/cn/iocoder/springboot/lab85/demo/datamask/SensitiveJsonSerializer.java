package cn.iocoder.springboot.lab85.demo.datamask;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * SensitiveJsonSerializer 数据脱敏 JSON 序列化方式
 *
 * @author jaquez
 * @date 2022/08/31 15:57
 **/
public class SensitiveJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizeRuleEnums rule;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeString(rule.desensitize().apply(value));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Desensitize annotation = property.getAnnotation(Desensitize.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass())) {
            this.rule = annotation.rule();
            return this;
        }
        return prov.findValueSerializer(property.getType(), property);
    }

}
