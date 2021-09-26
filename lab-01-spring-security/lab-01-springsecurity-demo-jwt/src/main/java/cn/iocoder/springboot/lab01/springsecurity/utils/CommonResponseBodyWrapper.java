package cn.iocoder.springboot.lab01.springsecurity.utils;

import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 响应数据类
 *
 * @author jaquez
 * @date 2021/09/25 18:48
 **/
public class CommonResponseBodyWrapper<T> implements Serializable {

    public static String CODE_SUCCESS = "200";

    /**
     * 响应码
     */
    private String code;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * 数据集
     */
    private T result;
    /**
     * token 凭证
     */
    private String token;

    public static <T> CommonResponseBodyWrapper<T> error(CommonResponseBodyWrapper<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static <T> CommonResponseBodyWrapper<T> error(String code, String msg) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "code error！");
        CommonResponseBodyWrapper<T> result = new CommonResponseBodyWrapper<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> CommonResponseBodyWrapper<T> success(T data) {
        CommonResponseBodyWrapper<T> result = new CommonResponseBodyWrapper<>();
        result.code = CODE_SUCCESS;
        result.result = data;
        result.msg = "";
        return result;
    }


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

    public CommonResponseBodyWrapper setResult(T result) {
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
