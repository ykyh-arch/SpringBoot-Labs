package cn.iocoder.springboot.lab85.demo.strategy;


import cn.iocoder.springboot.lab85.demo.enums.SensitiveDefaultLengthEnum;
import cn.iocoder.springboot.lab85.demo.utils.SensitiveInfoUtils;

/**
 * 默认脱敏策略，只显示六个 *
 *
 * @author Jaquez
 * @date 2022/09/05 16:34
 */
public class DefaultSensitiveStrategy implements IStrategy{

    @Override
    public String desensitization(String source, int begin, int end) {
        return SensitiveInfoUtils.password(source, SensitiveDefaultLengthEnum.DEFAULT_STRATEGY.getBegin());
    }
}
