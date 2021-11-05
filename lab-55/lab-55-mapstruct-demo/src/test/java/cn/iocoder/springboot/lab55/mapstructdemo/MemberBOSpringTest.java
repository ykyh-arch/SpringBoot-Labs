package cn.iocoder.springboot.lab55.mapstructdemo;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.MemberBO;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.MemberSpringConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.MemberDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 依赖注入测试
 *
 * @author jaquez
 * @date 2021/11/05 14:59
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberBOSpringTest {

    @Autowired
    private MemberSpringConvert memberSpringMapper;

    @Test
    public void test(){
        // 创建相关对象
        MemberDO memberDO = new MemberDO().setId(1L).setUsername("nicai")
                .setPassword("123456").setNickname("nicai")
                .setPhone("1822556987").setIcon("").setGender(0);
         MemberBO memberBO = memberSpringMapper.toBO(memberDO);
        System.out.println(memberBO);
    }
}
