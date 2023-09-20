package cn.iocoder.springboot.labs.chat03.demo1.model;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * UserModel
 *
 * @author fw001
 * @date 2023/09/19 15:26
 **/
@Data
@Builder
@Alias("user") // 类上有Alias注解，会取这个注解的value作为别名，如果没有这个注解，会将类名小写作为别名
public class UserModel {

    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private Integer sex;
}
