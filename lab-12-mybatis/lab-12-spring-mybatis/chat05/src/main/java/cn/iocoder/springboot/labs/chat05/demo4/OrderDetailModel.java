package cn.iocoder.springboot.labs.chat05.demo4;

import lombok.*;

/**
 * OrderDetailModel
 *
 * @author fw001
 * @date 2023/09/22 15:20
 **/
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailModel {

    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    private Integer num;
    private Double totalPrice;
}
