package cn.iocoder.springboot.lab24.apidoc.vo;

/**
 * @author jaquez
 * @date 2021/09/02 15:11
 **/
public class UserLoginReqVO {
    /**
     * 用户名
     */
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginReqVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginReqVO setPassword(String password) {
        this.password = password;
        return this;
    }
}
