package cn.iocoder.springboot.lab85.demo.strategy;

import cn.iocoder.springboot.lab85.demo.enums.SensitiveDefaultLengthEnum;
import cn.iocoder.springboot.lab85.demo.utils.SensitiveInfoUtils;

/**
 * 中文名称脱敏
 *
 * @author Jaquez
 * @date 2022/09/05 16:43
 */
public class SensitiveChineseName implements IStrategy {

    @Override
    public String desensitization(String source,int begin,int end) {
        if(begin != SensitiveDefaultLengthEnum.CHINESE_NAME.getBegin() && begin !=0){
            return SensitiveInfoUtils.chineseName(source,begin);
        }
        return SensitiveInfoUtils.chineseName(source, SensitiveDefaultLengthEnum.CHINESE_NAME.getBegin());
    }

}
