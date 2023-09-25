package cn.iocoder.springboot.labs.chat07;

import lombok.*;

import java.io.Serializable;

/**
 * UserModel
 *
 * @author fw001
 * @date 2023/09/25 16:31
 **/
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserModel  implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    // 对sex需要自定义的类型转换器
    private SexEnum sex;
}
