package cn.iocoder.springboot.lab76.idempotent.utils;

/**
 * 通用常量类
 *
 * @author jaquez
 * @date 2021/08/27 15:07
 **/
public class Constant {

    public static final String TOKEN_NAME = "token";
    public static final String SUCCESS = "SUCCESS";
    public static Integer CODE_SUCCESS = 1;

    public static class ResponseCode {

        public static final String ILLEGAL_ARGUMENT = "不合法的参数，缺少 token";
        public static final String REPETITIVE_OPERATION = "重复请求";

    }

    public static class Redis {

        public static final String TOKEN_PREFIX = "token_";
    }


}
