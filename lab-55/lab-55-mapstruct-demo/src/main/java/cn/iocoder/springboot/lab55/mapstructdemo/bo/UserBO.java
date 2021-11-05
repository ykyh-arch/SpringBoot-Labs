package cn.iocoder.springboot.lab55.mapstructdemo.bo;

import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.UserDO;

import java.util.Date;

public class UserBO {

    /** 用户编号 **/
    private Integer id;
    /** 用户名 **/
    private String username;
    /** 密码 **/
    private String password;
    /** 生日 **/
    private String birthday;

    public Integer getId() {
        return id;
    }

    public UserBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserBO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public UserBO setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

}
