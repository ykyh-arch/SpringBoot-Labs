package cn.iocoder.springboot.labs.chat05.demo2;

import lombok.*;

/**
 * OrderModel
 *
 * @author fw001
 * @date 2023/09/21 16:07
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

    // 下单用户信息
    private UserModel userModel;
}
