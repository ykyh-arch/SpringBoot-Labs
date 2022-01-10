package cn.iocoder.springboot.lab12.mybatis.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * TenantHolder 类，存取 TENANT_ID
 *
 * @author jaquez
 * @date 2022/01/07 17:06
 **/
public class TenantHolder {

    private static final ThreadLocal<Integer> TENANT_ID = new TransmittableThreadLocal<>();
    // private static final ThreadLocal<Integer> TENANT_ID = new ThreadLocal<>();
    // private static final ThreadLocal<Integer> TENANT_ID = new InheritableThreadLocal<>();

    public static void setTenantId(Integer tenantId) {
        TENANT_ID.set(tenantId);
    }

    public static Integer getTenantId() {
        return TENANT_ID.get();
    }

}
