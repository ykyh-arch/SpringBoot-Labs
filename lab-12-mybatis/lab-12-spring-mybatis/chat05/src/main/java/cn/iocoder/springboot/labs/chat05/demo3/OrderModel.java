package cn.iocoder.springboot.labs.chat05.demo3;

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
public class OrderModel {

    private Integer id;
    private Integer userId;
    private Long createTime;
    private Long upTime;
    // 订单详情列表
    private List<OrderDetailModel> orderDetailModelList;

}
