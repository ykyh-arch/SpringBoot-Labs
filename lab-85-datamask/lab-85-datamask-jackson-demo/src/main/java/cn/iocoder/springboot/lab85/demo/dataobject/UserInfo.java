package cn.iocoder.springboot.lab85.demo.dataobject;

import cn.iocoder.springboot.lab85.demo.datamask.Desensitize;
import cn.iocoder.springboot.lab85.demo.datamask.DesensitizeRuleEnums;
import lombok.Data;

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

}
