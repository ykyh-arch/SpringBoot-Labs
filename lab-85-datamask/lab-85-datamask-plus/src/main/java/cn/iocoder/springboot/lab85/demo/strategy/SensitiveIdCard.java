package cn.iocoder.springboot.lab85.demo.strategy;


import cn.iocoder.springboot.lab85.demo.enums.SensitiveDefaultLengthEnum;
import cn.iocoder.springboot.lab85.demo.utils.SensitiveInfoUtils;

/**
 * 身份证号脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 16:48
 */
public class SensitiveIdCard implements IStrategy {

    @Override
    public String desensitization(String idCardNum,int begin ,int end) {
        if(begin != SensitiveDefaultLengthEnum.ID_CARD_NUM.getBegin() && begin !=0 &&
                end != SensitiveDefaultLengthEnum.ID_CARD_NUM.getEnd() && end !=0){
            return SensitiveInfoUtils.idCardNum(idCardNum,begin,end);
        }
        return SensitiveInfoUtils.idCardNum(idCardNum, SensitiveDefaultLengthEnum.ID_CARD_NUM.getBegin(), SensitiveDefaultLengthEnum.ID_CARD_NUM.getEnd());
    }

}
