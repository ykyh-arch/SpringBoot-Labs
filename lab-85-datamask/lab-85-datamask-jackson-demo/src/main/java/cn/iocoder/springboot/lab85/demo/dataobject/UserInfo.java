package cn.iocoder.springboot.lab85.demo.dataobject;

import cn.iocoder.springboot.lab85.demo.datamask.Desensitize;
import cn.iocoder.springboot.lab85.demo.datamask.DesensitizeRuleEnums;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * UserInfo
 *
 * @author jaquez
 * @date 2022/08/31 16:17
 **/
@Data
public class UserInfo {

    // 用户id
    private Long id;

    // 姓名
    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String name;

    // 邮箱
    @Desensitize(rule = DesensitizeRuleEnums.EMAIL)
    private String email;

    // 电话
    @Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
    private String phone;

    // 地址
    @Desensitize(rule = DesensitizeRuleEnums.ADDRESS)
    private String addr;

    // 创建时间
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    // 更新时间
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
