package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.annotation;

import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.support.IsolationLevel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 跨库事务功能
 * 通过指定 datasourceId 对应的数据源 id，默认不填则为主库
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiTransaction {

    String transactionManager() default "multiTransactionManager";

    // 默认数据隔离级别，随数据库本身默认值
    IsolationLevel isolationLevel() default IsolationLevel.DEFAULT;

    // 默认为主库数据源
    String datasourceId() default "default";

    // 只读事务，若有更新操作会抛出异常
    boolean readOnly() default false;

    int timeout() default -1;

    String timeoutString() default "";

    Class<? extends Throwable>[] rollbackFor() default {};

    String[] rollbackForClassName() default {};

    Class<? extends Throwable>[] noRollbackFor() default {};

    String[] noRollbackForClassName() default {};

}
