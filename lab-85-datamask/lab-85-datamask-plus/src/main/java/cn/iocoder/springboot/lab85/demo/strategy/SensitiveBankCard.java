package cn.iocoder.springboot.lab85.demo.strategy;


import cn.iocoder.springboot.lab85.demo.enums.SensitiveDefaultLengthEnum;
import cn.iocoder.springboot.lab85.demo.utils.SensitiveInfoUtils;

/**
 * 银行卡号脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 16:42
 */
public class SensitiveBankCard implements IStrategy {

    @Override
    public String desensitization(String bankCard,int begin, int end) {
        if(begin != SensitiveDefaultLengthEnum.BANKCARD.getBegin() && begin !=0 &&
                end != SensitiveDefaultLengthEnum.BANKCARD.getEnd() && end !=0){
            return SensitiveInfoUtils.bankCard(bankCard,begin,end);
        }
        return SensitiveInfoUtils.bankCard(bankCard, SensitiveDefaultLengthEnum.BANKCARD.getBegin(), SensitiveDefaultLengthEnum.BANKCARD.getEnd());
    }

}
