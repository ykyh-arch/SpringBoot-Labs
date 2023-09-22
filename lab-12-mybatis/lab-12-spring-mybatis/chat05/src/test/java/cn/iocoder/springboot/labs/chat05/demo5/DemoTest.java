package cn.iocoder.springboot.labs.chat05.demo5;

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
        String resource = "demo5/mybatis-config.xml";
        // 读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // 全局mybatis-config.xml中lazyLoadingEnabled、aggressiveLazyLoading中配置
    @Test
    public void getById1() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderUserOrderDetailModel orderModel = mapper.getById1(id);
            // log.info("{}", orderModel);
            log.info("-------分割线--------");
            log.info("{}", orderModel.getUserModel());
        }
    }

    // 立即加载与延迟加载，在sqlmap中配置
    @Test
    public void getById2() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            Integer id = 1;
            OrderUserOrderDetailModel orderModel = mapper.getById2(id);
            // log.info("{}", orderModel);
            log.info("-------分割线--------");
            log.info("{}", orderModel.getOrderDetailModelList());
        }
    }

    // 鉴定器，关闭懒加载配置测试，相关于java中switch
    @Test
    public void getById3() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            OrderUserOrderDetailModel orderModel = mapper.getById3(1);
            log.info("{}", orderModel);
            log.info("-------分割线--------");
            orderModel = mapper.getById3(2);
            log.info("{}", orderModel);
            log.info("-------分割线--------");
            orderModel = mapper.getById3(3);
            log.info("{}", orderModel);
        }
    }

    // 鉴定器 + 集成，关闭懒加载配置测试
    @Test
    public void getById4() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            OrderUserOrderDetailModel orderModel = mapper.getById4(1);
            log.info("{}", orderModel);
            log.info("-------分割线--------");
            orderModel = mapper.getById4(2);
            log.info("{}", orderModel);
            log.info("-------分割线--------");
            orderModel = mapper.getById4(3);
            log.info("{}", orderModel);
        }
    }

}
