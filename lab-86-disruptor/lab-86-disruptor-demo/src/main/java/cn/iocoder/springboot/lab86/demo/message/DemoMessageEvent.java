package cn.iocoder.springboot.lab86.demo.message;

import lombok.Data;

/**
 * DemoMessageEvent demo 消息事件
 *
 * @author jaquez
 * @date 2022/09/16 11:29
 **/
@Data
public class DemoMessageEvent {

    private Long id;

    private String message;
}
