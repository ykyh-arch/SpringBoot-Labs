package cn.iocoder.springboot.labs.chat03.demo3.model;

import lombok.Builder;
import lombok.Data;

/**
 * OrderModel
 *
 * @author fw001
 * @date 2023/09/20 15:22
 **/
@Data
@Builder
public class OrderModel {

    private Long id;
    private Long user_id;
    private Double price;
}
