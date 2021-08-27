package cn.iocoder.springboot.lab76.idempotent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot + redis 处理幂等性问题
 * @author Jaquez
 * @date 2021/08/27 14:41
 */
@SpringBootApplication
public class Application {
    /**
     * 如何保证其幂等性，通常有以下手段：
     * 数据库建立唯一性索引，可以保证最终插入数据库的只有一条数据
     * token机制，每次接口请求前先获取一个token，然后再下次请求的时候在请求的header体中加上这个token，后台进行验证，如果验证通过删除token，下次请求再次判断token
     * 悲观锁或者乐观锁，悲观锁可以保证每次for update的时候其他sql无法update数据(在数据库引擎是innodb的时候,select的条件必须是唯一索引,防止锁全表)
     * 先查询后判断，首先通过查询数据库是否存在数据，如果存在证明已经请求过了，直接拒绝该请求，如果没有存在，就证明是第一次进来，直接放行。
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
