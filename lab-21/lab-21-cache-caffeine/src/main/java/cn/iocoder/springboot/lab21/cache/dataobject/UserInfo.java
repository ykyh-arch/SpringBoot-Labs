package cn.iocoder.springboot.lab21.cache.dataobject;

import lombok.Data;
import lombok.ToString;

/**
 * UserInfo
 *
 * @author jaquez
 * @date 2022/06/28 16:58
 **/

@Data
@ToString
public class UserInfo {

    private Integer id;
    private String name;
    private String sex;
    private Integer age;
}
