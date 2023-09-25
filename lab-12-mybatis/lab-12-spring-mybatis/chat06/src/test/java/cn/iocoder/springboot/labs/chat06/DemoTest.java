package cn.iocoder.springboot.labs.chat06;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/09/21 16:18
 **/
@Slf4j
public class DemoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        // 指定mybatis全局配置文件
        String resource = "mybatis-config.xml";
        // 读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Test
    public void insertBatch() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModelList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                userModelList.add(UserModel.builder().id(10 + i).name("mybatis-" + i).age(20 + i).build());
            }
            int count = mapper.insertBatch(userModelList);
            log.info("{}", count);
        }
    }

    @Test
    public void getModelList() throws IOException {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("likeName","java");
            List<UserModel> userModelList = mapper.getList6(paramMap);
            log.info("{}", userModelList);
        }
    }

    @Test
    public void getList7() throws IOException {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            String SQL = "order by id asc,age desc";
            List<UserModel> userModelList = mapper.getList7(SQL);
            log.info("{}", userModelList);
        }
    }

}
