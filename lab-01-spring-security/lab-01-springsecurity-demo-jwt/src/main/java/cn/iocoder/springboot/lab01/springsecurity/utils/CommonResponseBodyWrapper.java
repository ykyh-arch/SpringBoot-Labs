package cn.iocoder.springboot.lab01.springsecurity.utils;

import java.io.Serializable;

/**
 * 响应数据类
 *
 * @author jaquez
 * @date 2021/09/25 18:48
 **/
public class CommonResponseBodyWrapper implements Serializable {

    private String code;
    private String msg;
    private Object result;
    private String token;

    public String getCode() {
        return code;
    }

    public CommonResponseBodyWrapper setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public CommonResponseBodyWrapper setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public CommonResponseBodyWrapper setResult(Object result) {
        this.result = result;
        return this;
    }

    public String getToken() {
        return token;
    }

    public CommonResponseBodyWrapper setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "AjaxResponseBodyWrapper{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                ", token='" + token + '\'' +
                '}';
    }
}
