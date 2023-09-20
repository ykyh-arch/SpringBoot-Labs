package cn.iocoder.springboot.labs.chat03.demo2;

import lombok.Builder;
import lombok.Data;

/**
 * UserModel
 *
 * @author fw001
 * @date 2023/09/19 15:26
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
