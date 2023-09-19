package cn.iocoder.springboot.labs.chat02;

import lombok.Builder;
import lombok.Data;

/**
 * UserModel
 *
 * @author fw001
 * @date 2023/09/18 17:53
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
