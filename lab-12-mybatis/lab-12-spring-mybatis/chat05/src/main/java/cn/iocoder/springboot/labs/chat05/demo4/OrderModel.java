package cn.iocoder.springboot.labs.chat05.demo4;

import lombok.*;

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

}
