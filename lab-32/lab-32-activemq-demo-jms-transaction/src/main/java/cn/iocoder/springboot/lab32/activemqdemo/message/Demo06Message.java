package cn.iocoder.springboot.lab32.activemqdemo.message;

import java.io.Serializable;

/**
 * 消息类
 */
public class Demo06Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_06";

    /**
     * 编号
     */
    private Integer id;

    public Demo06Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClusteringtMessage{" +
                "id=" + id +
                '}';
    }

}
