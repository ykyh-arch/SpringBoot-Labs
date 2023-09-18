package cn.iocoder.springboot.labs.chat01;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * UserUtil
 *
 * @author fw001
 * @date 2023/09/15 16:54
 **/
@Slf4j
public class UserUtil {

    private static SqlSessionFactory sqlSessionFactory = build();

    // SqlSessionFactory是一个重量级的对象，一般一个db对应一个SqlSessionFactory对象，系统运行过程中会一直存在
    public static SqlSessionFactory build() {
        try {
            return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface SessionCall<O> {
        O call(SqlSession session) throws Exception;
    }

    @FunctionalInterface
        public interface MapperCall<T, O> {
            O call(T mapper) throws Exception;
    }

    public static <T, O> O callMapper(Class<T> tClass, MapperCall<T, O> mapper) throws Exception {
        // Object result = sqlSession.selectOne("namespace.statementId", parameter); // 可以直接通过sqlSession实现对xml文件中定义的语句进行操作，但一般都是通过xml与之映射的Mapper接口文件进行操作
        return call(session -> mapper.call(session.getMapper(tClass)));
    }

    public static <O> O call(SessionCall<O> sessionCall) throws Exception {
        // 类似于jdbc中Connection连接对象，方法级别的Sql会话对象
        try (SqlSession session = sqlSessionFactory.openSession(true);) {
            return sessionCall.call(session);
        }
    }

}
