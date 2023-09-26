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

    /**
     *
     * 一级缓存测试
     */
    @Test
    public void getList1() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 第一次查询
            List<UserModel> userModelList1 = mapper.getList1(null);
            log.info("{}", userModelList1);
            // 第二次查询
            List<UserModel> userModelList2 = mapper.getList1(null);
            log.info("{}", userModelList2);
            log.info("{}", userModelList1 == userModelList2);
        }
    }

    /**
     * 增删改使一级缓存失效
     *
     * @throws IOException
     */
    @Test
    public void level1CacheTest1() throws IOException {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 第一次查询
            List<UserModel> userModelList1 = mapper.getList1(null);
            log.info("{}", userModelList1);
            // 新增一条数据
            mapper.insert(UserModel.builder().id(100).name("路人").age(30).build());
            // 第二次查询
            List<UserModel> userModelList2 = mapper.getList1(null);
            log.info("{}", userModelList2);
            log.info("{}", userModelList1 == userModelList2);
        }
    }

    /**
     * 调用sqlSession.clearCache()清理一级缓存
     *
     * @throws IOException
     */
    @Test
    public void level1CacheTest2() throws IOException {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 第一次查询
            List<UserModel> userModelList1 = mapper.getList1(null);
            log.info("{}", userModelList1);
            // 调用clearCache方法清理当前SqlSession中的缓存
            sqlSession.clearCache();
            // 第二次查询
            List<UserModel> userModelList2 = mapper.getList1(null);
            log.info("{}", userModelList2);
            log.info("{}", userModelList1 == userModelList2);
        }
    }

    /**
     * 将Mapper xml中select元素的flushCache属性置为true的时候，每次执行这个select元素对应的查询之前，mybatis会将当前SqlSession中一级缓存中的所有数据都清除。
     *
     * @throws IOException
     */
    @Test
    public void level1CacheTest3() throws IOException {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 查询1：getList1
            log.info("查询1 start");
            log.info("查询1：getList1->{}", mapper.getList1(null));
            // 查询2：getList1
            log.info("查询2 start");
            log.info("查询2：getList1->{}", mapper.getList1(null));
            // 查询3：getList8
            log.info("查询3 start");
            log.info("查询3：getList8->{}", mapper.getList8(null));
            //查询4：getList8
            log.info("查询4 start");
            log.info("查询4：getList8->{}", mapper.getList8(null));
            //查询5：getList1
            log.info("查询5 start");
            log.info("查询5：getList1->{}", mapper.getList1(null));
        }
    }

    /**
     * 二级缓存测试
     *
     * @throws IOException
     */
    @Test
    public void level2CacheTest1() throws IOException {
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
                UserMapper mapper = sqlSession.getMapper(UserMapper.class);
                log.info("{}", mapper.getList1(null));
            }
        }
    }

    /**
     * 增删改会清除二级缓存中的数据
     *
     * @throws IOException
     */
    @Test
    public void level2CacheTest2() throws IOException {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModelList1 = mapper.getList1(null);
            log.info("{}", userModelList1);
        }
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 新增一条数据
            mapper.insert(UserModel.builder().id(Integer.valueOf(System.nanoTime() % 100000 + "")).name("路人").age(30).build());
        }
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModelList1 = mapper.getList1(null);
            log.info("{}", userModelList1);
        }
    }

    /**
     * 当将mapper xml中select元素的flushCache属性置为true，会先清空二级缓存中的数据，然后再去db中查询数据，然后将数据再放到二级缓存中
     *
     * @throws IOException
     */
    @Test
    public void level2CacheTest3() throws IOException {
        // 先查询2次getList1,getList1第二次会从二级缓存中拿到数据
        log.info("getList1查询");
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
                UserMapper mapper = sqlSession.getMapper(UserMapper.class);
                List<UserModel> userModelList1 = mapper.getList1(null);
                log.info("{}", userModelList1);
            }
        }
        // getList8的flushCache为true，所以查询之前会先将对应的二级缓存中的所有数据清空，所以二次都会访问db
        log.info("getList2查询");
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
                UserMapper mapper = sqlSession.getMapper(UserMapper.class);
                List<UserModel> userModelList1 = mapper.getList8(null);
                log.info("{}", userModelList1);
            }
        }

        // 二级缓存中没有getList1需要查找的数据了，所以这次访问getList1会去访问db
        log.info("getList1查询");
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModelList1 = mapper.getList1(null);
            log.info("{}", userModelList1);
        }

    }

    /**
     * select元素的useCache置为false跳过二级缓存，但是不会情况二级缓存数据
     *
     * @throws IOException
     */
    @Test
    public void level2CacheTest4() throws IOException {
        // 第一次查询访问getList1，会将数据丢到二级缓存中
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModelList1 = mapper.getList1(null);
            log.info("{}", userModelList1);
        }

        // getList9对应的select的useCache为false，会跳过二级缓存，所以会直接去访问db
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
                UserMapper mapper = sqlSession.getMapper(UserMapper.class);
                List<UserModel> userModelList1 = mapper.getList9(null);
                log.info("{}", userModelList1);
            }
        }

        // 下面的查询又去执行了getList1，由于上面的第一次查询也是访问getList1会将数据放在二级缓存中，所以下面的查询会从二级缓存中获取到数据
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModelList1 = mapper.getList1(null);
            log.info("{}", userModelList1);
        }
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
