package cn.iocoder.springboot.labs.chat05.demo4;

import lombok.*;

import java.util.List;

/**
 * OrderModel
 *
 * @author fw001
 * @date 2023/09/22 10:39
 **/
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderOrderDetailModel {

    private Integer id;
    private Integer userId;
    private Long createTime;
    private Long upTime;
    // 订单明细信息
    private List<OrderDetailModel> orderDetailModelList;

}
