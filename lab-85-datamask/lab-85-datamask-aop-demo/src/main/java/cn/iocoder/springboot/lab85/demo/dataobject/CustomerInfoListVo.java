package cn.iocoder.springboot.lab85.demo.dataobject;

import cn.iocoder.springboot.lab85.demo.datamask.ReadableSensitiveTypeEnum;
import cn.iocoder.springboot.lab85.demo.datamask.ReadableSensitiveVerify;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * CustomerInfoListVo 客户信息
 *
 * @author jaquez
 * @date 2022/08/31 16:56
 **/
@Data
@EqualsAndHashCode()
public class CustomerInfoListVo implements Serializable {

    // 记录id
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    // 创建时间
    private Date createTime;

    // 客户名称
    @ReadableSensitiveVerify(ReadableSensitiveTypeEnum.NAME)
    private String realName;

    // 手机号码
    @ReadableSensitiveVerify(ReadableSensitiveTypeEnum.PHONE)
    private String phone;

    // 客户身份证号
    @ReadableSensitiveVerify(ReadableSensitiveTypeEnum.ID_CARD)
    private String idCarNumber;

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

}
