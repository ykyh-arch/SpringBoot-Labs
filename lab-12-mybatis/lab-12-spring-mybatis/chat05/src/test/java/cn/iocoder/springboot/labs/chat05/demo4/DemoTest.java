package cn.iocoder.springboot.labs.chat05.demo4;

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
        String resource = "demo4/mybatis-config.xml";
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
            Integer id = 1;
            OrderModel orderModel = mapper.getById2(id);
            log.info("{}", orderModel);
        }
    }

    // 对应mybatis-config.xml中autoMappingBehavior=NONE时
    @Test
    public void getById3() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderModel orderModel = mapper.getById3(id);
            log.info("{}", orderModel);
        }
    }

    // 对应mybatis-config.xml中autoMappingBehavior=PARTIAL时
    @Test
    public void getById4() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderModel orderModel = mapper.getById4(id);
            log.info("{}", orderModel);
        }
    }

    // 对应mybatis-config.xml中autoMappingBehavior=PARTIAL时
    @Test
    public void getById5() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderUserModel orderModel = mapper.getById5(id);
            log.info("{}", orderModel);
        }
    }

    // 对应mybatis-config.xml中autoMappingBehavior=FULL时
    @Test
    public void getById6() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderUserModel orderModel = mapper.getById6(id);
            log.info("{}", orderModel);
        }
    }

    // 对应mybatis-config.xml中autoMappingBehavior=NONE时
    @Test
    public void getById7() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderUserModel orderModel = mapper.getById7(id);
            log.info("{}", orderModel);
        }
    }

    // 对应mybatis-config.xml中autoMappingBehavior=FULL时
    @Test
    public void getById8() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderOrderDetailModel orderModel = mapper.getById8(id);
            log.info("{}", orderModel);
        }
    }

}
