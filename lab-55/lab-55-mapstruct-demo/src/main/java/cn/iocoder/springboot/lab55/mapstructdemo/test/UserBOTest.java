package cn.iocoder.springboot.lab55.mapstructdemo.test;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.UserBO;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.UserConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.UserDO;

import java.util.Date;

/**
 * 基本映射测试
 */
public class UserBOTest {

    public static void main(String[] args) {
        // 创建 UserDO 对象
        UserDO userDO = new UserDO()
                .setId(1).setUsername("yudaoyuanma").setPassword("buzhidao").setBirthday(new Date());
        // 进行转换
        UserBO userBO = UserConvert.INSTANCE.convert(userDO);
        System.out.println(userBO.getId());
        System.out.println(userBO.getUsername());
        System.out.println(userBO.getPassword());
        System.out.println(userBO.getBirthday());
    }

}
