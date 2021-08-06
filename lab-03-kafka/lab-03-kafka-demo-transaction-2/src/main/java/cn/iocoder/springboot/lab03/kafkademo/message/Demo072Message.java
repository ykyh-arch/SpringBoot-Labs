package cn.iocoder.springboot.lab03.kafkademo.message;

/**
 * 示例 072 的 Message 消息
 *
 * @author jaquez
 * @date 2021/08/06 10:30
 **/
public class Demo072Message {
    public static final String TOPIC = "DEMO_072";

    /**
     * 编号
     */
    private Integer id;

    public Demo072Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Demo07Message{" +
                "id=" + id +
                '}';
    }
}
