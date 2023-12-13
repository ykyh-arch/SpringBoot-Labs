package cn.iocoder.springboot.labs.statemachine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Order
 *
 * @author fw001
 * @date 2023/12/12 17:38
 **/
@Data
@TableName(value = "tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String orderCode;
    private Integer status;
    private String name;
    private BigDecimal price;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
    private String createUserCode;
    private String updateUserCode;
    private Integer version;
    private String remark;

}
