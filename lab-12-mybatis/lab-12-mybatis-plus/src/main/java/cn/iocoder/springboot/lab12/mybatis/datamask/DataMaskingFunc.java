package cn.iocoder.springboot.lab12.mybatis.datamask;

import org.apache.logging.log4j.util.Strings;
import org.apache.commons.lang3.StringUtils;

/**
 * 枚举
 *
 * @author Jaquez
 * @date 2022/08/31 13:59
 */
public enum DataMaskingFunc {

    /**
     *  脱敏转换器
     */
    NO_MASK((str, maskChar) -> {
        return str;
    }),
    PART_MASK((str, maskChar) -> {
        if (!Strings.isEmpty(str)) {
            int length = str.length();
            if(length == 2){
                return "*".concat(str.substring(1));
            }else if(length == 3){
                return StringUtils.left(str,1).concat("*").concat(StringUtils.right(str,1));
            }else if(length > 3){
                return StringUtils.left(str,1).concat(generateAsterisk(str.substring(1,length-1).length())).concat(StringUtils.right(str,1));
            }else {
                return str;
            }
        }
        return str;
    }),
    ALL_MASK((str, maskChar) -> {
        if (org.springframework.util.StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(org.springframework.util.StringUtils.hasLength(maskChar) ? maskChar : DataMaskingOperation.MASK_CHAR);
            }
            return sb.toString();
        } else {
            return str;
        }
    });

    private final DataMaskingOperation operation;

    private DataMaskingFunc(DataMaskingOperation operation) {
        this.operation = operation;
    }

    public DataMaskingOperation operation() {
        return this.operation;
    }

    /**
     * @description: 返回指定长度 * 字符串
     * @return:
     */
    private static String generateAsterisk(int length){
        String result = "";
        for (int i = 0; i < length; i++) {
            result += "*";
        }
        return result;
    }

}
