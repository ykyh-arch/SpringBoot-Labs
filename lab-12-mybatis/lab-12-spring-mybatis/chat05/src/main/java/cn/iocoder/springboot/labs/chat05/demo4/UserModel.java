package cn.iocoder.springboot.labs.chat05.demo4;

import lombok.*;

/**
 * UserModel
 *
 * @author fw001
 * @date 2023/09/22 15:01
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

}
