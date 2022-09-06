package cn.iocoder.springboot.lab85.demo.strategy;


import cn.iocoder.springboot.lab85.demo.enums.SensitiveDefaultLengthEnum;
import cn.iocoder.springboot.lab85.demo.utils.SensitiveInfoUtils;

/**
 * 地址脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 16:39
 */
public class SensitiveAddress implements IStrategy {

    @Override
    public String desensitization(String address,int begin, int end) {
        if(begin != SensitiveDefaultLengthEnum.ADDRESS.getBegin() && begin !=0 ){
            return SensitiveInfoUtils.address(address,begin);
        }
        return SensitiveInfoUtils.address(address, SensitiveDefaultLengthEnum.ADDRESS.getBegin());
    }

}
