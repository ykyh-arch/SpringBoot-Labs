package cn.iocoder.springboot.lab16.springdatamongodb.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Mongo新增事件监听器
 * 实现方式二：创建自定义 @AutoIncKey 注解，添加到 ID 属性上。
 * @author jaquez
 * @date 2021/08/01 19:03
 **/
@Component
public class MongoSaveEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        if (source != null) {

            // 处理类字段信息
            ReflectionUtils.doWithFields(source.getClass(),new ReflectionUtils.FieldCallback(){
                @Override
                public void doWith(Field field)throws IllegalArgumentException,IllegalAccessException {
                    //将一个字段设置为可读写，主要针对private字段；
                    ReflectionUtils.makeAccessible(field);
                    // 如果字段添加了我们自定义的AutoValue注解
                    if (field.isAnnotationPresent(AutoIncKey.class)
                            && field.get(source) instanceof Number
                            && field.getLong(source) == 0) {
                        // 设置自增ID
                        field.set(source, getNextAutoId(source.getClass().getSimpleName()));
                    }
                }
            });
        }
    }

    // 1.8 以前实现方法如下
//    @Override
//    public void onBeforeConvert(final Object source) {
//        if (source != null) {
//            ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
//                public void doWith(Field field) throws
//                IllegalArgumentException,   IllegalAccessException {
//                    ReflectionUtils.makeAccessible(field);
//                    // 如果字段添加了我们自定义的AutoIncKey注解
//                    if (field.isAnnotationPresent(AutoIncKey.class)) {
//                        // 设置自增ID
//                        field.set(source, getNextAutoId(source.getClass().getSimpleName()));
//                    }
//                }
//            });
//        }
//    }

    // 获取下一个自增ID
    private Long getNextAutoId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        // 向集合sequence_1操作数据
        SeqInfo seq = mongoTemplate.findAndModify(query, update, options, SeqInfo.class);
        return seq.getSeqId();
    }

}
