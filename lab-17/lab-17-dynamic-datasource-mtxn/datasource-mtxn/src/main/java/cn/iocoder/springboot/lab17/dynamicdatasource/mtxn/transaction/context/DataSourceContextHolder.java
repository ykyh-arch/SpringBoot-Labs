package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.context;

/**
 * 数据源上下文 key 环境
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();

    private DataSourceContextHolder() {
        throw new IllegalStateException("Utils");
    }

    public static String getKey() {
        return THREAD_LOCAL.get();
    }

    public static synchronized void setKey(String key) {
        THREAD_LOCAL.set(key);
    }

    public static void clearKey() {
        THREAD_LOCAL.remove();
    }
}
