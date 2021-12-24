package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.Application;
import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.insert.render.BatchInsert;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mybatis.dynamic.sql.SqlBuilder.insertBatch;

/**
 * sql 插入测试
 *
 * 参考：https://mp.weixin.qq.com/s/M0v8h06P88xfeFZCbK8gBw
 *
 * @author Jaquez
 * @date 2021/12/23 17:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SqlInsertTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public List<UserDO> list = new ArrayList<>();

    private final SqlTable users = SqlTable.of("users");
    // private final SqlColumn<Integer> id = user.column("id", JDBCType.INTEGER);
    private final SqlColumn<String> username = users.column("username", JDBCType.VARCHAR);
    private final SqlColumn<String> password = users.column("password", JDBCType.VARCHAR);
    private final SqlColumn<Date> createTime = users.column("create_time", JDBCType.DATE);

    /**
     * 数据准备 50000 条数据
     * @author Jaquez
     * @date 2021/12/23 17:23
     * @return void
     */
    @Before
    public void  getList() {
        long start = System.currentTimeMillis();
        UserDO user;
        for (int i = 1; i <=50000 ; i++) {
            user = new UserDO().setUsername(UUID.randomUUID().toString())
                    .setPassword("mima:"+i).setCreateTime(new Date());
            list.add(user);
        }
        System.out.println("拼装数据 耗时："+(System.currentTimeMillis()-start)); // 拼装数据 耗时：1172
        System.out.println(list.size());
    }

    /**
     * sqlSession 批量插入，参考：https://mp.weixin.qq.com/s/LYNPG8xghpERTJpsddt29A
     * @author Jaquez
     * @date 2021/12/23 17:20
     * @return void
     */
    @Test
    public void batchInsert1() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("batchInsert1 插入开始");
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            // 利用 mp 插入方法
            mapper.insert(list.get(i));

            if (i%5000 == 4999) {
                sqlSession.flushStatements();
//                sqlSession.commit();
//                sqlSession.clearCache();
            }
        }
//        sqlSession.commit();
//        sqlSession.clearCache();
        sqlSession.flushStatements();
        System.out.println("SqlSession 批量插入耗时："+(System.currentTimeMillis()-start));// SqlSession 批量插入耗时：78782
    }

    /**
     * 参考：https://mybatis.org/mybatis-dynamic-sql/docs/insert.html
     * 项目参考：https://github.com/mybatis/mybatis-dynamic-sql
     *
     * @author Jaquez
     * @date 2021/12/23 20:20
     * @return void
     */
    @Test
    public void batchInsert2() {

        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        long start = System.currentTimeMillis();
        try {
            UserMapper2 mapper = session.getMapper(UserMapper2.class);
            List<UserDO> records = list; // not shown

            BatchInsert<UserDO> batchInsert = insertBatch(records)
                .into(users)
                .map(username).toProperty("username")
                .map(password).toProperty("password")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategies.MYBATIS3);

            System.out.println("结果为:\n"+batchInsert.getInsertStatementSQL());
            batchInsert.insertStatements().stream().forEach(mapper::insert);

            session.commit();
        } finally {
            session.close();
        }
        System.out.println("SqlSession 批量插入耗时："+(System.currentTimeMillis()-start));// SqlSession 批量插入耗时：75348
    }

    /**
     * eachInsert 追条插入
     * @author Jaquez
     * @date 2021/12/23 17:46
     * @return void
     */
    @Test
    public void forEachInsert() {
        System.out.println("forEachInsert 插入开始");
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            userMapper.insert(list.get(i));
        }
        System.out.println("foreach 插入耗时："+(System.currentTimeMillis()-start)); // foreach 插入耗时：91816
    }

    /**
     * sql insert sql插入
     * @author Jaquez
     * @date 2021/12/23 18:00
     * @return void
     */
    @Test
    public void sqlInsert() {
        System.out.println("sql 插入开始");
        long start = System.currentTimeMillis();
        userMapper.sqlInsert(list);
        System.out.println("sql 插入耗时："+(System.currentTimeMillis()-start)); // sql 插入耗时：2007
    }

    // xml批量插入
    @Test
    public void xmlInsert() {
        System.out.println("xmlInsert 批量插入开始");
        long start = System.currentTimeMillis();
        userMapper.xmlBatchInsert(list);
        System.out.println("xmlInsert 批量插入耗时："+(System.currentTimeMillis()-start)); // xmlInsert 批量插入耗时：3418
    }

    // 结论：sql 插入的效率最高，mybatis 框架 foreach 次之，sqlsession 再之（可能与前一项对调，当表的列数较多（20+），以及一次性插入的行数较多（5000+）时，foreach 特别耗时）， 单行插入效率最低。

}
