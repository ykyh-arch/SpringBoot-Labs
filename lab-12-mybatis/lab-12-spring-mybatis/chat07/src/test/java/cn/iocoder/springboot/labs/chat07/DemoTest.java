package cn.iocoder.springboot.labs.chat07;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
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
    public void getList1() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> paramMap = new HashMap<>();
            List<UserModel> userModelList = mapper.getList1(paramMap);
            log.info("{}", userModelList);
        }
    }

    @Test
    public void insert1() throws IOException {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            Integer id = Integer.valueOf(Calendar.getInstance().getTime().getTime() / 1000 + "");
            map.put("id", id);
            map.put("name", id.toString());
            map.put("age", 30);
            // map.put("sex", SexEnum.WOMAN);
            map.put("sex", null); // 在mysql库中不会报错，在其他数据库如：oracle可能会报错
            int result = mapper.insert1(map);
            log.info("{}", result);
        }
    }

}
