package cn.iocoder.springboot.labs.chat06;

import lombok.*;

/**
 * UserModel
 *
 * @author fw001
 * @date 2023/09/21 16:43
 **/
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Integer id;
    private String name;
    private Integer age;
}
