
package cn.iocoder.springboot.lab33.shirodemo.oauth2;

import cn.iocoder.springboot.lab33.shirodemo.exception.MyException;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * token 生成器
 * @author Jaquez
 * @date 2021/10/05 15:07
 */
public class OAuth2TokenGenerator {

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new MyException("生成Token失败", e);
        }
    }
}
