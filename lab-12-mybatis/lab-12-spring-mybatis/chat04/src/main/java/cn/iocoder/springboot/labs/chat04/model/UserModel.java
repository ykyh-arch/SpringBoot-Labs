package cn.iocoder.springboot.labs.chat04.model;

import lombok.Builder;
import lombok.Data;

/**
 * UserModel
 *
 * @author fw001
 * @date 2023/09/20 16:14
 **/
@Data
@Builder
public class UserModel {

    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private Integer sex;
}
