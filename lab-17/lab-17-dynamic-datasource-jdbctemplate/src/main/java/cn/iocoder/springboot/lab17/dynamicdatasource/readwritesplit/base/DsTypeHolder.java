package cn.iocoder.springboot.lab17.dynamicdatasource.readwritesplit.base;

/**
 * DsTypeHolder
 *
 * @author fw001
 * @date 2023/09/12 15:15
 **/
public class DsTypeHolder {

    private static ThreadLocal<DsType> dsTypeThreadLocal = new ThreadLocal<>();

    public static void master() {
        dsTypeThreadLocal.set(DsType.MASTER);
    }

    public static void slave() {
        dsTypeThreadLocal.set(DsType.SLAVE);
    }

    public static DsType getDsType() {
        return dsTypeThreadLocal.get();
    }

    public static void clearDsType() {
        dsTypeThreadLocal.remove();
    }
}
