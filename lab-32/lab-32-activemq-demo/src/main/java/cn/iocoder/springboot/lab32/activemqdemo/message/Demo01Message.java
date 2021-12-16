package cn.iocoder.springboot.lab32.activemqdemo.message;

import java.io.Serializable;

/**
 * 消息类 Demo01Message，要实现 Java Serializable 序列化接口。因为 JMS 规范要求 POJO 消息类，需要实现 Serializable 接口。
 */
public class Demo01Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_01";

    /**
     * 编号
     */
    private Integer id;

    public Demo01Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo01Message{" +
                "id=" + id +
                '}';
    }

}
