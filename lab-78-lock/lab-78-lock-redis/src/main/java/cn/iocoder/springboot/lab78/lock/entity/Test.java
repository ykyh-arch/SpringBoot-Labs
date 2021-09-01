package cn.iocoder.springboot.lab78.lock.entity;

import cn.iocoder.springboot.lab78.lock.annoation.CacheParam;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 测试实体
 *
 * @author jaquez
 * @date 2021/09/01 13:44
 **/
public class Test implements Serializable {

    @CacheParam(name="id")
    private String id;

    @CacheParam(name="name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
