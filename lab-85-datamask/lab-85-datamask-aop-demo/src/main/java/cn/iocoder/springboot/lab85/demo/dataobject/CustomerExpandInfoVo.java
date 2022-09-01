package cn.iocoder.springboot.lab85.demo.dataobject;

import cn.iocoder.springboot.lab85.demo.datamask.ReadableSensitiveTypeEnum;
import cn.iocoder.springboot.lab85.demo.datamask.ReadableSensitiveVerify;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * CustomerExpandInfoVo
 *
 * @author jaquez
 * @date 2022/09/01 10:13
 **/
@Data
@EqualsAndHashCode()
public class CustomerExpandInfoVo {

    // 客户信息id
    private Integer customerInfoId;

    // 性别
    private String gender;

    // 出生日期
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date birthDate;

    // 客户编号
    private String customerCode;

    // 邮箱
    @ReadableSensitiveVerify(ReadableSensitiveTypeEnum.EMAIL)
    private String email;

    // 客户经理
    private String customerManager;

    // 微信号
    private String weChat;

    // 拉黑时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date blockingTime;

    // 拉黑说明
    private String blockInstructions;

    // 意向等级
    private String interestingGrade;

    // 客户地址
    @ReadableSensitiveVerify(ReadableSensitiveTypeEnum.ADDRESS)
    private String customerAddr;
}
