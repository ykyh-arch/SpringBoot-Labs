package cn.iocoder.springboot.lab01.springsecurity.utils;

/**
 * 系统枚举类
 * @author Jaquez
 * @date 2021/09/25 19:07
 */
public enum SysResponseCodeAndMsgEnum {
    NOT_AUTHENTICATION(000, "Need Authentication!"),
    LOGOUT_SUCCESS(100, "Logout Success!"),
    LOGIN_SUCCESS(200, "Login Success!"),
    NOT_AUTHORITIES(300, "Need Authorities!"),
    LOGIN_FAILURE(400, "Login Failure!"),

    ERROR(401, "ERROR!"),
    ;

    private final int code;

    private final String msg;

    SysResponseCodeAndMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
