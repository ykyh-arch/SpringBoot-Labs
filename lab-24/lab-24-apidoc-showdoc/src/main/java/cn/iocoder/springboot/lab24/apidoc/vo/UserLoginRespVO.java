package cn.iocoder.springboot.lab24.apidoc.vo;

/**
 * @author jaquez
 * @date 2021/09/02 15:12
 **/
public class UserLoginRespVO {

    private Integer userId;

    private String name;

    private String username;

    public Integer getUserId() {
        return userId;
    }

    public UserLoginRespVO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserLoginRespVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserLoginRespVO setUsername(String username) {
        this.username = username;
        return this;
    }
}
