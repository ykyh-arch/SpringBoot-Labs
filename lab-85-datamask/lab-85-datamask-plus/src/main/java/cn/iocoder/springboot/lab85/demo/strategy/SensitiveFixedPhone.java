package cn.iocoder.springboot.lab85.demo.strategy;

import cn.iocoder.springboot.lab85.demo.enums.SensitiveDefaultLengthEnum;
import cn.iocoder.springboot.lab85.demo.utils.SensitiveInfoUtils;

/**
 * 固话脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 16:45
 */
public class SensitiveFixedPhone implements IStrategy {

    @Override
    public String desensitization(String fixedPhone,int begin ,int end) {
        if(begin != SensitiveDefaultLengthEnum.FIXED_PHONE.getBegin() && begin !=0 &&
                end != SensitiveDefaultLengthEnum.FIXED_PHONE.getEnd() && end !=0){
            return SensitiveInfoUtils.fixedPhone(fixedPhone,end);
        }
        return SensitiveInfoUtils.fixedPhone(fixedPhone, SensitiveDefaultLengthEnum.FIXED_PHONE.getEnd());
    }

}
