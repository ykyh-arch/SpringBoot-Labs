package cn.iocoder.springboot.lab85.demo.datamask;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DesensitizationUtils 数据脱敏工具类，可使用 Hutools 工具类代替
 *
 * @author jaquez
 * @date 2022/08/31 17:04
 **/
public class DesensitizationUtils {

    /**
     * @description: 名字脱敏
     * 脱敏规则: 隐藏中中间部分，比如:李某人 置换为 李*人 , 李某置换为 *某，司徒司翘置换为 司**翘
     * @return:
     * @author: Ming
     * @time: 2022/6/22
     */
    public static String desensitizedName(String fullName){
        if (!Strings.isEmpty(fullName)) {
            int length = fullName.length();
            if(length == 2){
                return "*".concat(fullName.substring(1));
            }else if(length == 3){
                return StringUtils.left(fullName,1).concat("*").concat(StringUtils.right(fullName,1));
            }else if(length > 3){
                return StringUtils.left(fullName,1).concat(generateAsterisk(fullName.substring(1,length-1).length())).concat(StringUtils.right(fullName,1));
            }else {
                return fullName;
            }
        }
        return fullName;
    }


    /**
     * @description: 手机号脱敏，脱敏规则: 保留前三后四, 比如15566026528置换为155****6528
     * @return:
     * @author: Ming
     * @time: 2022/6/22
     */
    public static String desensitizedPhoneNumber(String phoneNumber){
        if(StringUtils.isNotEmpty(phoneNumber)){
            int length = phoneNumber.length();
            if(length == 11){
                return phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
            }else if(length > 2){
                return StringUtils.left(phoneNumber,1).concat(generateAsterisk(phoneNumber.substring(1,length-2).length())).concat(StringUtils.right(phoneNumber,1));
            }else {
                return phoneNumber;
            }
        }
        return phoneNumber;
    }


    /**
     * @description: 身份证脱敏
     * 脱敏规则: 保留前六后三, 适用于15位和18位身份证号：
     * 原身份证号(15位)：210122198401187，脱敏后的身份证号：210122******187
     * 原身份证号(18位)：210122198401187672，脱敏后的身份证号：210122*********672
     * @return:
     * @author: Ming
     * @time: 2022/6/22
     */
    public static String desensitizedIdNumber(String idNumber){
        if (!Strings.isEmpty(idNumber)) {
            int length = idNumber.length();
            if (length == 15){
                return idNumber.replaceAll("(\\w{6})\\w*(\\w{3})", "$1******$2");
            }else if (length == 18){
                return idNumber.replaceAll("(\\w{6})\\w*(\\w{3})", "$1*********$2");
            }else if(length > 9){
                return StringUtils.left(idNumber,6).concat(generateAsterisk(idNumber.substring(6,length-3).length())).concat(StringUtils.right(idNumber,3));
            }
        }
        return idNumber;
    }


    /**
     * @description: 电子邮箱脱敏，脱敏规则：电子邮箱隐藏@前面的3个字符
     * @return:
     * @author: Ming
     * @time: 2022/6/22
     */
    public static String desensitizationEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return email;
        }
        String encrypt = email.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
        if (email.equalsIgnoreCase(encrypt)) {
            encrypt = email.replaceAll("(\\w*)\\w{1}@(\\w+)", "$1*@$2");
        }
        return encrypt;
    }


    /**
     * @description: 地址脱敏，脱敏规则：从第4位开始隐藏,隐藏8位
     * @return:
     * @author: Ming
     * @time: 2022/6/22
     */
    public static String desensitizedAddress(String address){
        if (!Strings.isEmpty(address)) {
            int length = address.length();
            if(length > 4 && length <= 12){
                return StringUtils.left(address, 3).concat(generateAsterisk(address.substring(3).length()));
            }else if(length > 12){
                return StringUtils.left(address,3).concat("********").concat(address.substring(11));
            }else {
                return address;
            }
        }
        return address;
    }


    /**
     * @description: 银行账号脱敏, 脱敏规则：银行账号保留前六后四
     * @return:
     * @author: Ming
     * @time: 2022/6/23
     */
    public static String desensitizedAddressBankCardNum(String acctNo) {
        if (StringUtils.isNotEmpty(acctNo)) {
            String regex = "(\\w{6})(.*)(\\w{4})";
            Matcher m = Pattern.compile(regex).matcher(acctNo);
            if (m.find()) {
                String rep = m.group(2);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < rep.length(); i++) {
                    sb.append("*");
                }
                acctNo = acctNo.replaceAll(rep, sb.toString());
            }
        }
        return acctNo;
    }


    /**
     * @description: 返回指定长度*字符串
     * @return:
     * @author: Ming
     * @time: 2022/6/22
     */
    private static String generateAsterisk(int length){
        String result = "";
        for (int i = 0; i < length; i++) {
            result += "*";
        }
        return result;
    }

}
