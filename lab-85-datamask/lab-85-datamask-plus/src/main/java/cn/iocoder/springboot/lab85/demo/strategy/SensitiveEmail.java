package cn.iocoder.springboot.lab85.demo.strategy;

import cn.iocoder.springboot.lab85.demo.enums.SensitiveDefaultLengthEnum;
import cn.iocoder.springboot.lab85.demo.utils.SensitiveInfoUtils;

/**
 * 电子邮箱脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 16:45
 */
public class SensitiveEmail implements IStrategy {

    @Override
    public String desensitization(String email,int begin,int end) {
        if(begin != SensitiveDefaultLengthEnum.EMAIL.getBegin() && begin !=0 ){
            return SensitiveInfoUtils.email(email,begin);
        }
        return SensitiveInfoUtils.email(email, SensitiveDefaultLengthEnum.EMAIL.getBegin());
    }

}
