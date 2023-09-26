package cn.iocoder.springboot.labs.chat06;

import lombok.*;

import java.io.Serializable;

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
public class UserModel implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
}
