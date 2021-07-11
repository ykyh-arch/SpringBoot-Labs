package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PipelineTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Pipeline 操作
     * @author Jaquez
     * @date 2021/07/13 21:07
     * @return void
     */
    @Test
    public void test01() {
        List<Object> results = stringRedisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                // set 写入
                for (int i = 0; i < 3; i++) {
                    //提供了更底层的 Redis API 操作。已经序列化的入参和会被反序列化的出参
                    connection.set(String.format("yunai:%d", i).getBytes(), "shuai".getBytes());
                }

                // get
                for (int i = 0; i < 3; i++) {
                    connection.get(String.format("yunai:%d", i).getBytes());
                }

                // 返回 null 即可
                return null;
            }
        });

        // 打印结果
        System.out.println(results);
    }

}
