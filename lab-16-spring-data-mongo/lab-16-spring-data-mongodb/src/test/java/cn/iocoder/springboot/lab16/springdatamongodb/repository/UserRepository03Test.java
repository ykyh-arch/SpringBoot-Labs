package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.Application;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRepository03Test {

    @Autowired
    private UserRepository03 userRepository;

    @Test
    public void testFindByUsername01() {
        UserDO user = userRepository.findByUsername01("yutou");
        System.out.println(user);
    }

    // 查询符合条件的一条数据
    @Test
    public void testFindByUsernameLike01() {
        UserDO user = userRepository.findByUsernameLike01("yu");
        System.out.println(user);
    }

}
