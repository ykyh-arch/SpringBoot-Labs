package cn.iocoder.springboot.labs.chat05.demo3;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/09/22 10:59
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
    public void getById1() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderModel orderModel = mapper.getById1(id);
            log.info("{}", orderModel);
        }
    }

    @Test
    public void getById2() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            OrderModel orderModel = mapper.getById2(1);
            log.info("{}", orderModel);
        }
    }

}
