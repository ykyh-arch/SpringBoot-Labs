package cn.iocoder.springboot.lab55.mapstructdemo.dataobject;

import java.util.Date;

/**
 * 会员数据对象
 *
 * @author jaquez
 * @date 2021/11/04 17:24
 **/
public class MemberDO {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Date birthday;
    private String phone;
    private String icon;
    private Integer gender;

    public Long getId() {
        return id;
    }

    public MemberDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MemberDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MemberDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public MemberDO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public MemberDO setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public MemberDO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public MemberDO setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public MemberDO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return "MemberDo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", icon='" + icon + '\'' +
                ", gender=" + gender +
                '}';
    }
}
