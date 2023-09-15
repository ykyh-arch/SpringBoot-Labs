package cn.iocoder.springboot.labs.chat01;

import lombok.*;

/**
 * UserModel
 *
 * @author fw001
 * @date 2023/09/15 16:42
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserModel {

    private Long id;
    private String name;
    private Integer age;
    private Double salary;
}
