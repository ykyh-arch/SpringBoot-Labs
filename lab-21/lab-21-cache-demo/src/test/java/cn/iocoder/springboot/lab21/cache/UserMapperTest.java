package cn.iocoder.springboot.lab21.cache;

import cn.iocoder.springboot.lab21.cache.dataobject.UserDO;
import cn.iocoder.springboot.lab21.cache.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectById() {
        // sql 只执行了一次，缓存起作用
        UserDO user = userMapper.selectById(1);
        System.out.println("user：" + user);
        user = userMapper.selectById(1);
        System.out.println("user：" + user);
    }

    @Test
    public void testInsert () {
        // 插入记录
        UserDO user = new UserDO();
        // 随机账号，因为唯一索引
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword("nicai");
        user.setCreateTime(new Date());
        user.setDeleted(0);
        userMapper.insert0(user);

        // 查询数据
        user = userMapper.selectById(user.getId());
        System.out.println(user);
    }

}
