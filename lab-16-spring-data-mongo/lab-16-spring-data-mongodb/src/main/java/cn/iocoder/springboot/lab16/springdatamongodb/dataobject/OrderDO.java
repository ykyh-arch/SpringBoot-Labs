package cn.iocoder.springboot.lab16.springdatamongodb.dataobject;

import cn.iocoder.springboot.lab16.springdatamongodb.mongo.AutoIncKey;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 订单测试类
 *
 * @author jaquez
 * @date 2021/08/01 19:38
 **/
public class OrderDO {

    @AutoIncKey
    @Id
    public long id;
    @Field
    public String name;

    public OrderDO(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
