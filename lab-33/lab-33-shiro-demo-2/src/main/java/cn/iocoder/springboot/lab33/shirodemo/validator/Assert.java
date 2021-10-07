
package cn.iocoder.springboot.lab33.shirodemo.validator;

import cn.iocoder.springboot.lab33.shirodemo.exception.MyException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author Jaquez
 * @date 2021/10/06 11:47
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new MyException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new MyException(message);
        }
    }
}
