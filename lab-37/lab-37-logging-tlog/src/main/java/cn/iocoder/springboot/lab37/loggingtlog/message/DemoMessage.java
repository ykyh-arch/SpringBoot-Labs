package cn.iocoder.springboot.lab37.loggingtlog.message;

import java.io.Serializable;

/**
 * Direct Exchange
 */
public class DemoMessage implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO";

    public static final String EXCHANGE = "EXCHANGE_DEMO";

    public static final String ROUTING_KEY = "ROUTING_KEY_DEMO";

    /**
     * 编号
     */
    private Integer id;

    public DemoMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DemoMessage{" +
                "id=" + id +
                '}';
    }

}
