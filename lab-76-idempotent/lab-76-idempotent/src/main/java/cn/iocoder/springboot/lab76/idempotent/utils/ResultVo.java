package cn.iocoder.springboot.lab76.idempotent.utils;

/**
 * 结果封装类
 *
 * @author jaquez
 * @date 2021/08/27 16:03
 **/
public class ResultVo<T>{

    private Integer code;

    private String message;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultVo getFailedResult(Integer code, String message) {
        ResultVo vo = new ResultVo();
        vo.setCode(code);
        vo.setMessage(message);
        vo.setData(null);
        return vo;
    }

    public static ResultVo getSuccessResult(String businessResult) {
        ResultVo vo = new ResultVo();
        vo.setCode(Constant.CODE_SUCCESS);
        vo.setMessage(Constant.SUCCESS);
        vo.setData(businessResult);
        return vo;
    }
}
