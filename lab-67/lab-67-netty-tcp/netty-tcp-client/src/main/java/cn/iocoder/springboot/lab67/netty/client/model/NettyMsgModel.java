package cn.iocoder.springboot.lab67.netty.client.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * NettyMsgModel 消息体
 *
 */
@Data
@Accessors(chain = true)
public class NettyMsgModel implements Serializable {

    private String imei;

    private String msg;

    private Map<String, Object> bizData;

    public static NettyMsgModel create(String imei, String msg) {
        return new NettyMsgModel().setBizData(new HashMap<>()).setMsg(msg).setImei(imei);
    }
}
