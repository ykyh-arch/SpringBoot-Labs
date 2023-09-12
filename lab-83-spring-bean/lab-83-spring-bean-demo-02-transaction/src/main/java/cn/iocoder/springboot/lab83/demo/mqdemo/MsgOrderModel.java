package cn.iocoder.springboot.lab83.demo.mqdemo;

import lombok.Builder;
import lombok.Data;

/**
 * MsgOrderModel
 *
 * @author fw001
 * @date 2023/09/11 17:13
 **/
@Data
@Builder
public class MsgOrderModel {

    // 即msg_order_id
    private Long id;
    // 关联业务类型
    private Integer ref_type;
    // 关联业务id（ref_type & ref_id 唯一）
    private String ref_id;
}
