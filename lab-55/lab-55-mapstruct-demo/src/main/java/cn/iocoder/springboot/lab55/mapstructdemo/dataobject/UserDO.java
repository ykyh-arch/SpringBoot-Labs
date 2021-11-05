package cn.iocoder.springboot.lab55.mapstructdemo.dataobject;

import java.util.Date;

/**
 * 用户 DO
 */
public class UserDO {

    /** 用户编号 **/
    private Integer id;
    /** 用户名 **/
    private String username;
    /** 密码 **/
    private String password;
    /** 生日 **/
    private Date birthday;

    public Integer getId() {
        return id;
    }

    public UserDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public UserDO setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }
}
