package cn.iocoder.springboot.lab83.demo.mqdemo;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.*;

/**
 * MsgModel
 *
 * @author fw001
 * @date 2023/09/11 17:11
 **/
@Data
@Builder
public class MsgModel {

    private Long id;
    // 消息内容
    private String msg;
    // 消息订单id
    private Long msg_order_id;
    // 消息状态,0:待投递，1：已发送，2：取消发送
    private Integer status;
}
