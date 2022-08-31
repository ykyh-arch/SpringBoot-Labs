package cn.iocoder.springboot.lab85.demo.response;

import lombok.Data;

/**
 * @author jaquez
 * @date 2022/08/31 18:15
 **/
@Data
public class R<T> {

    private Integer code;

    private String msg;

    private Page<T> obj;

    public R<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> setObj(Page<T> obj) {
        this.obj = obj;
        return this;
    }
}
