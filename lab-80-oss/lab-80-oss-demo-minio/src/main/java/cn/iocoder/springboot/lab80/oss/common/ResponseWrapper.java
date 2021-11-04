package cn.iocoder.springboot.lab80.oss.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 响应对象封装
 *
 * @author Jaquez
 * @date 2021/11/01 18:50
 */
public class ResponseWrapper<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Integer ERRROR = 500;

    private static Integer SUCCESS = 200;

    private Integer code;
    private E data;
    private String message = "";

    public ResponseWrapper(Integer code, E data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <E> ResponseWrapper<E> error(String message){
        return new ResponseWrapper(ResponseWrapper.ERRROR,"",message);
    }

    public static <E>  ResponseWrapper<E> success(E data,String message){
        return new ResponseWrapper(ResponseWrapper.SUCCESS,data, StringUtils.isEmpty(message)?"success":message);
    }

}
