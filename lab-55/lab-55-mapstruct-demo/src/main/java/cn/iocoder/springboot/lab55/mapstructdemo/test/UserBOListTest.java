package cn.iocoder.springboot.lab55.mapstructdemo.test;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.UserBO;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.UserConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.UserDO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 集合映射测试
 */
public class UserBOListTest {

    public static void main(String[] args) {
        // 创建 UserDO 对象
        List<UserDO> userDOList = new ArrayList<>();
        UserDO userDO1 = new UserDO()
                .setId(1).setUsername("yudaoyuanma").setPassword("buzhidao").setBirthday(new Date());
        UserDO userDO2 = new UserDO()
                .setId(2).setUsername("yudaoyuanma").setPassword("buzhidao").setBirthday(new Date());
        userDOList.add(userDO1);
        userDOList.add(userDO2);
        // 进行转换
        List<UserBO> userBOList = UserConvert.INSTANCE.convertList(userDOList);
        userBOList.forEach(userBO->{
            System.out.println(userBO.getId());
            System.out.println(userBO.getUsername());
            System.out.println(userBO.getPassword());
            System.out.println(userBO.getBirthday());
        });

    }

}
