package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.support;

import lombok.Builder;
import lombok.Data;

import java.util.Deque;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 跨库事务线程上下文数据
 * 每开启一个事物，生成一个事务 ID 并绑定一个 ConnectionProxy。事务嵌套调用，保存事务 ID 和 lookupKey 至栈中，当内层事务执行完毕执行 pop。这样的话，外层事务只需在栈中执行 peek 即可获取事务 ID 和 lookupKey。
 */
@Data
@Builder
public class TransactionHolder {
    // 是否开启了一个 MultiTransaction
    private boolean isOpen;
    // 是否只读事务
    private boolean readOnly;
    // 事务隔离级别
    private IsolationLevel isolationLevel;
    // 维护当前线程事务 ID 和连接关系
    private ConcurrentHashMap<String, ConnectionProxy> connectionMap;
    // 事务执行栈
    private Deque<String> executeStack;
    // 数据源切换栈
    private Deque<String> datasourceKeyStack;
    // 主事务 ID
    private String mainTransactionId;
    // 执行次数
    private AtomicInteger transCount;

    // 事务和数据源 key 关系
    private ConcurrentHashMap<String, String> executeIdDatasourceKeyMap;


    public void addCount() {
        transCount.incrementAndGet();
    }

    public int getCount() {
        return transCount.get();
    }

    public void clear() {
        connectionMap.clear();
        executeStack.clear();
        datasourceKeyStack.clear();
        executeIdDatasourceKeyMap.clear(); // 补充
    }
}
