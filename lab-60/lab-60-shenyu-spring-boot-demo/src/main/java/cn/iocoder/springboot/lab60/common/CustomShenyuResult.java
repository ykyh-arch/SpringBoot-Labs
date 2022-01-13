package cn.iocoder.springboot.lab60.common;

import org.apache.shenyu.plugin.api.result.ShenyuResult;

/**
 * 自定义返回结果包装类
 *
 * @author jaquez
 * @date 2022/01/13 16:09
 **/
public class CustomShenyuResult implements ShenyuResult<CustomShenyuResult> {

    public static Integer CODE_SUCCESS = 0;

    public static Integer CODE_ERROR = 1;

    private Integer code;

    private String message;

    private Object data;

    public CustomShenyuResult() {
    }

    public CustomShenyuResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static CustomShenyuResult success() {
        return success("");
    }

    public static CustomShenyuResult success(String msg) {
        return success(msg, (Object)null);
    }

    public static CustomShenyuResult success(Object data) {
        return success((String)null, data);
    }

    public static CustomShenyuResult success(String msg, Object data) {
        return get(CODE_SUCCESS, msg, data);
    }

    public static CustomShenyuResult error(String msg) {
        return error(CODE_ERROR, msg);
    }

    public static CustomShenyuResult error(int code, String msg) {
        return get(code, msg, (Object)null);
    }

    private static CustomShenyuResult get(int code, String msg, Object data) {
        return new CustomShenyuResult(code, msg, data);
    }

    @Override
    public CustomShenyuResult success(int code, String msg, Object data) {
        return get(code, msg, data);
    }

    @Override
    public CustomShenyuResult error(int code, String msg, Object data) {
        return get(code, msg, data);
    }
}
