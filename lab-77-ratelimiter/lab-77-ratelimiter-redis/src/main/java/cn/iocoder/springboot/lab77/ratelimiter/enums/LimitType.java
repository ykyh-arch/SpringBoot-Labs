package cn.iocoder.springboot.lab77.ratelimiter.enums;

public enum LimitType {
    /**
     * 自定义key
     */
    CUSTOMER,
    /**
     * 根据请求者IP
     */
    IP;
}
