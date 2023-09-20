package cn.iocoder.springboot.labs.chat03.demo3;

import cn.iocoder.springboot.labs.chat03.demo3.mapper.OrderMapper;
import cn.iocoder.springboot.labs.chat03.demo3.mapper.UserMapper;
import cn.iocoder.springboot.labs.chat03.demo3.model.OrderModel;
import cn.iocoder.springboot.labs.chat03.demo3.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/09/20 15:31
 **/
@Slf4j
public class DemoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        // 指定mybatis全局配置文件
        String resource = "demo3/mybatis-config.xml";
        // 读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Test
    public void test() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行查询操作
            List<UserModel> userModelList = userMapper.getList();
            userModelList.forEach(item -> {
                log.info("{}", item);
            });

            log.info("----------------------------------");
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行查询操作
            List<OrderModel> orderModelList = orderMapper.getList();
            orderModelList.forEach(item -> {
                log.info("{}", item);
            });
        }
    }

}
