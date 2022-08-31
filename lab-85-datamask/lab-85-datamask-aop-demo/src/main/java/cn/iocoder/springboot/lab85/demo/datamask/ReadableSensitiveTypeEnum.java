package cn.iocoder.springboot.lab85.demo.datamask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据脱敏枚举类
 *
 * @author Jaquez
 * @date 2022/08/31 16:50
 */
@Getter
@AllArgsConstructor
public enum ReadableSensitiveTypeEnum {

    /**
     * 身份证编号
     */
    ID_CARD("身份证"),

    /**
     * 地址/住址
     */
    ADDRESS("地址"),

    /**
     * 姓名
     */
    NAME("姓名"),

    /**
     * 手机号
     */
    PHONE("手机号"),

    /**
     * 手机号
     */
    EMAIL("邮箱"),

    /**
     * 银行卡号
     */
    BANK_CARD_NO("银行卡号");

    private String desc;
}
