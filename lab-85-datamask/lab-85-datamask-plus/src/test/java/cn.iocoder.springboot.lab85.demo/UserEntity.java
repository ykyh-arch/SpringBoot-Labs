package cn.iocoder.springboot.lab85.demo;

import cn.iocoder.springboot.lab85.demo.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体
 *
 * @author Jaquez
 * @date 2022/09/05 17:55
 */
@Data
@Builder
public class UserEntity implements Serializable {

    /**
     * 中文姓名--正则
     */
    @SensitiveChineseName
    private String userNamePattern;

    /**
     * 中文姓名--长度
     */
    @SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveChineseName.class, begin = 1)
    private String userNameLength;

    /**
     * 密码--正则
     */
    @SensitivePassword
    private String passwordPattern;

    /**
     * 密码--长度
     */
    @SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitivePassword.class, begin = 6)
    private String passwordLength;

    /**
     * 身份证--正则
     */
    @SensitiveIdCard
    private String idCardPattern;

    /**
     * 身份证--长度
     */
    @SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveIdCard.class, end = 4)
    private String idCardLength;

    /**
     * 固话--正则
     */
    @SensitiveFixedPhone
    private String fixedPhonePattern;

    /**
     * 固话--长度
     */
    @SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveFixedPhone.class, end = 4)
    private String fixedPhoneLength;

    /**
     * 手机--正则
     */
    @SensitiveMobile
    private String mobilePattern;

    /**
     * 手机--长度
     */
    @SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveMobile.class,begin = 3,end = 4)
    private String mobileLength;

    /**
     * 地址--正则
     */
    @SensitiveAddress
    private String addressPattern;

    /**
     * 地址--长度
     */
    @SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveAddress.class, begin = 6)
    private String addressLength;

    /**
     * 邮箱--正则
     */
    @SensitiveEmail
    private String emailPattern;

    /**
     * 邮箱--长度
     */
    @SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveEmail.class, begin = 1)
    private String emailLength;

    /**
     * 银行卡号--正则
     */
    @SensitiveBankCard
    private String bankCardPattern;

    /**
     * 银行卡号--自定义正则
     */
    @SensitiveInfo(pattern = "(?<=\\w{6})\\w(?=\\w{4})",replaceChar = "*")
    private String bankCardCustomizePattern;

    /**
     * 银行卡号--长度
     */
    @SensitiveInfo(strategy = cn.iocoder.springboot.lab85.demo.strategy.SensitiveBankCard.class, begin = 6, end = 4)
    private String bankCardLength;
}
