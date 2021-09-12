package cn.iocoder.springcloudalibaba.labx7.dto;

import java.io.Serializable;

/**
 * 用户添加 DTO，实现序列化，因为 Dubbo RPC 会涉及远程通信，需要序列化和反序列化
 */
public class UserAddDTO implements Serializable {

    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;

    public String getName() {
        return name;
    }

    public UserAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserAddDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

}
