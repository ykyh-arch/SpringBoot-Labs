package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.Application;
import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString())
                .setPassword("nicai").setCreateTime(new Date())
                // 一般情况下，是否删除，可以全局枚举下。
                .setDeleted(0);
        userMapper.insert(user);
    }

    @Test
    public void testUpdateById() {
        UserDO updateUser = new UserDO().setId(10)
                .setPassword("wobucaishagua");
        userMapper.updateById(updateUser);
    }

    @Test
    public void testDeleteById() {
        // Mybatis 查询如果配置了逻辑删除的字段，删除的是逻辑删除
        userMapper.deleteById(10);
    }

    @Test
    public void testSelectById() {
        // Mybatis 查询如果配置了逻辑删除的字段，查询出来的是正常的数据
        userMapper.selectById(10);
    }

    @Test
    public void testSelectByUsername() {
        userMapper.selectByUsername("yunai");
    }

    @Test
    public void testSelectByIds() {
        List<UserDO> users = userMapper.selectByIds(Arrays.asList(7, 10));
        System.out.println("users：" + users.size());
    }

    @Test
    public void testSelectPageByCreateTime() {
        IPage<UserDO> page = new Page<>(1, 10);
        Date createTime = new Date(2018 - 1990, Calendar.FEBRUARY, 24); // 临时 Demo ，实际不建议这么写
        page = userMapper.selectPageByCreateTime(page, createTime);
        System.out.println("users：" + page.getRecords().size());
    }

}
