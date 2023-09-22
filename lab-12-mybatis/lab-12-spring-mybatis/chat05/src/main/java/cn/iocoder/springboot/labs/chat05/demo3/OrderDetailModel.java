package cn.iocoder.springboot.labs.chat05.demo3;

import lombok.*;

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
