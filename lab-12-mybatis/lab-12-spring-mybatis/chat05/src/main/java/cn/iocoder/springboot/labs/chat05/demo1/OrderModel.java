package cn.iocoder.springboot.labs.chat05.demo1;

import cn.iocoder.springboot.labs.chat05.demo2.UserModel;
import lombok.Builder;
import lombok.Data;

/**
 * OrderModel
 *
 * @author fw001
 * @date 2023/09/21 16:07
 **/
@Data
@Builder
public class OrderModel {

    private Integer id;
    private Integer userId;
    private Long createTime;
    private Long upTime;

}
