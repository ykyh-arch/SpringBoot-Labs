package cn.iocoder.springboot.lab17.dynamicdatasource.service;

import cn.iocoder.springboot.lab17.dynamicdatasource.constant.DBConstants;
import cn.iocoder.springboot.lab17.dynamicdatasource.dataobject.OrderDO;
import cn.iocoder.springboot.lab17.dynamicdatasource.dataobject.UserDO;
import cn.iocoder.springboot.lab17.dynamicdatasource.mapper.OrderMapper;
import cn.iocoder.springboot.lab17.dynamicdatasource.mapper.UserMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 当前类 Bean已经被AOP代理
     * @author Jaquez
     * @date 2021/06/12 18:52
     * @return cn.iocoder.springboot.lab17.dynamicdatasource.service.OrderService
     */
    private OrderService self() {
        return (OrderService) AopContext.currentProxy();
    }

    public void method01() {
        // 查询订单
        OrderDO order = orderMapper.selectById(1);
        System.out.println(order);
        // 查询用户
        UserDO user = userMapper.selectById(1);
        System.out.println(user);
    }

    /**
     * 事务-》AOP-》代理-》拦截器（transactionInterceptor）-》委托使用DataSourceTransactionManager-》
     * DynamicRoutingDataSource（维护多个实现类，如果通过@DS方式指定，则使用对应数据源，如果未指定则使用默认数据源
     * 则DS信息则通过上下文ThreadLocal绑定）=》DataSource-》Collection
     */
    @Transactional
    public void method02() {
        // 查询订单
        OrderDO order = orderMapper.selectById(1);
        System.out.println(order);
        // 查询用户
        UserDO user = userMapper.selectById(1);
        System.out.println(user);
    }

    public void method03() {
        // 查询订单
        self().method031();
        // 查询用户
        self().method032();
    }

    @Transactional
    public void method031() {
        // 报错，因为此时获取的是 primary 对应的 DataSource ，即 users 。如果换成this.method031()则测试通过
        // this 指定当前类，则不走代理逻辑，自然AOP事务则失效！
        OrderDO order = orderMapper.selectById(1);
        System.out.println(order);
    }

    @Transactional
    public void method032() {
        UserDO user = userMapper.selectById(1);
        System.out.println(user);
    }

    //DynamicRoutingDataSource
    public void method04() {
        // 查询订单
        self().method041();
        // 查询用户
        self().method042();
    }

    /**
     * 在 Spring 事务机制中，在一个事务执行完成后，会将事务信息和当前线程解绑。
     * 所以，在执行 #method042() 方法前，又可以执行一轮事务的逻辑。
     */
    @Transactional
    @DS(DBConstants.DATASOURCE_ORDERS)
    public void method041() {
        OrderDO order = orderMapper.selectById(1);
        System.out.println(order);
    }

    @Transactional
    @DS(DBConstants.DATASOURCE_USERS)
    public void method042() {
        UserDO user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Transactional
    @DS(DBConstants.DATASOURCE_ORDERS)
    public void method05() {
        // 查询订单
        OrderDO order = orderMapper.selectById(1);
        System.out.println(order);
        // 查询用户
        self().method052();
    }

    /**
     * Propagation.REQUIRES_NEW：开启一个新的事务，原事务与当前线程解绑挂起，等新事物执行完成后，
     * 唤醒原事务，并与当前线程重新绑定继续执行
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DS(DBConstants.DATASOURCE_USERS)
    public void method052() {
        UserDO user = userMapper.selectById(1);
        System.out.println(user);
    }

}
