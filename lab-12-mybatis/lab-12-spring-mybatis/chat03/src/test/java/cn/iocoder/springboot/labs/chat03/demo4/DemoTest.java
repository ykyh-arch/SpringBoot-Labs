package cn.iocoder.springboot.labs.chat03.demo4;

import cn.iocoder.springboot.labs.chat03.demo4.dto.UserFindDto;
import cn.iocoder.springboot.labs.chat03.demo4.mapper.UserMapper;
import cn.iocoder.springboot.labs.chat03.demo4.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.result.DefaultResultContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DemoTest
 *
 * @author fw001
 * @date 2023/09/20 16:36
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

    /**
     * 通过单个参数给Mapper接口的方法传递参数
     */
    @Test
    public void getByName() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserModel userModel = userMapper.getByName("路人甲Java");
            log.info("{}", userModel);
        }
    }

    /**
     * 通过map给Mapper接口的方法传递参数
     */
    @Test
    public void getByMap() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1L);
            map.put("name", "张学友");
            List<UserModel> userModelList = userMapper.getByMap(map);
            log.info("{}", userModelList);
        }
    }

    @Test
    public void getListByUserFindDto() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserFindDto userFindDto = UserFindDto.builder().userId(1L).userName("张学友").build();
            List<UserModel> userModelList = userMapper.getListByUserFindDto(userFindDto);
            userModelList.forEach(item -> {
                log.info("{}", item);
            });
        }
    }

    @Test
    public void getByIdOrName() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserModel userModel = userMapper.getByIdOrName(1L, "路人甲Java");
            log.info("{}", userModel);
        }
    }

    @Test
    public void getListByIdCollection() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> userIdList = Arrays.asList(1L, 3L);
            List<UserModel> userModelList = userMapper.getListByIdCollection(userIdList);
            userModelList.forEach(item -> {
                log.info("{}", item);
            });
        }
    }

    @Test
    public void getListByIdList() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> userIdList = Arrays.asList(1L, 3L);
            List<UserModel> userModelList = userMapper.getListByIdList(userIdList);
            userModelList.forEach(item -> {
                log.info("{}", item);
            });
        }
    }

    @Test
    public void getListByIdArray() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Long[] userIdList = new Long[]{1L, 3L};
            List<UserModel> userModelList = userMapper.getListByIdArray(userIdList);
            userModelList.forEach(item -> {
                log.info("{}", item);
            });
        }
    }

    @Test
    public void getList() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModelList = userMapper.getList(context -> {
                // 将context参数转换为DefaultResultContext对象
                DefaultResultContext<UserModel> defaultResultContext = (DefaultResultContext<UserModel>) context;
                log.info("{}", defaultResultContext.getResultObject());
                // 遍历到第二条之后停止
                if (defaultResultContext.getResultCount() >= 2) {
                    // 调用stop方法停止遍历，stop方法会更新内部的一个标志，置为停止遍历
                    defaultResultContext.stop();
                }
            });
            userModelList.forEach(item -> {
                log.info("{}", item);
            });
        }
    }

}
