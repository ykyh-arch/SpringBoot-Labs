package cn.iocoder.springboot.lab17.dynamicdatasource.transaction;

import cn.iocoder.springboot.lab17.dynamicdatasource.transaction.service.TestService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 程序测试类，参考自：https://blog.didispace.com/spring-boot-learning-24-3-12/
 *
 * @author jaquez
 * @date 2022/05/30 11:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    protected JdbcTemplate primaryJdbcTemplate;
    @Autowired
    protected JdbcTemplate secondaryJdbcTemplate;

    @Autowired
    private TestService testService;

    @Test
    public void test1() throws Exception {

        // 正确更新的情况
        testService.testTransaction1();
        Assertions.assertEquals(18, primaryJdbcTemplate.queryForObject("select age from user where name=?", Integer.class, "Jaquez"));
        Assertions.assertEquals(18, secondaryJdbcTemplate.queryForObject("select age from user where name=?", Integer.class, "Jaquez"));
    }

    @Test
    public void test2() throws Exception {
        // 更新失败的情况
        try {
            testService.testTransaction2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 部分更新失败，lab-17-transactions-1 中的更新应该回滚
            Assertions.assertEquals(18, primaryJdbcTemplate.queryForObject("select age from user where name=?", Integer.class, "Jaquez"));
            Assertions.assertEquals(18, secondaryJdbcTemplate.queryForObject("select age from user where name=?", Integer.class, "Jaquez"));
        }
    }

}
