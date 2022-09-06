package cn.iocoder.springboot.lab85.demo.strategy;


import cn.iocoder.springboot.lab85.demo.enums.SensitiveDefaultLengthEnum;
import cn.iocoder.springboot.lab85.demo.utils.SensitiveInfoUtils;

/**
 * 手机号脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 16:48
 */
public class SensitiveMobile implements IStrategy {

    @Override
    public String desensitization(String mobile,int begin ,int end) {
        if(begin != SensitiveDefaultLengthEnum.MOBILE.getBegin() && begin !=0 &&
                end != SensitiveDefaultLengthEnum.MOBILE.getEnd() && end !=0){
            return SensitiveInfoUtils.mobilePhone(mobile,begin,end);
        }
        return SensitiveInfoUtils.mobilePhone(mobile, SensitiveDefaultLengthEnum.MOBILE.getBegin(), SensitiveDefaultLengthEnum.MOBILE.getEnd());
    }

}
