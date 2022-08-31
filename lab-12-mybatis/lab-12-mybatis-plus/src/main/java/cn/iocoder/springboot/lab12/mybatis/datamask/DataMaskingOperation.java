package cn.iocoder.springboot.lab12.mybatis.datamask;

/**
 * DataMaskingOperation
 *
 * @author jaquez
 * @date 2022/08/30 16:23
 **/
@FunctionalInterface
public interface DataMaskingOperation {

    String MASK_CHAR = "*";

    String mask(String content, String maskChar);
}
