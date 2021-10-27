package cn.iocoder.springboot.lab04.rabbitmqdemo.message;

import java.io.Serializable;

public class Demo082Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_082"; // 正常队列

    public static final String EXCHANGE = "EXCHANGE_DEMO_082";

    public static final String ROUTING_KEY = "ROUTING_KEY_082"; // 正常路由键

    /**
     * 编号
     */
    private Integer id;

    public Demo082Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo08Message{" +
                "id=" + id +
                '}';
    }

}
