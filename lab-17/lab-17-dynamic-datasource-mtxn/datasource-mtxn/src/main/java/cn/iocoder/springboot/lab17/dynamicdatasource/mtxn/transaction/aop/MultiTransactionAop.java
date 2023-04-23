package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.aop;

import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.application.Application;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.MultiTransactionManager;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.annotation.MultiTransaction;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.context.DataSourceContextHolder;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.support.IsolationLevel;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.support.TransactionHolder;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * 多数据源事务切面处理
 */
@Aspect
@Component
@Slf4j
@Order(99999)
public class MultiTransactionAop {

    @Pointcut("@annotation(cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.transaction.annotation.MultiTransaction)")
    public void pointcut() {
        if (log.isDebugEnabled()) {
            log.debug("start in transaction pointcut...");
        }
    }

    /**
     * 事务切面处理逻辑
     */
    @Around("pointcut()")
    public Object aroundTransaction(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 从切面中获取当前方法
        Method method = signature.getMethod();
        MultiTransaction multiTransaction = method.getAnnotation(MultiTransaction.class);
        if (multiTransaction == null) {
            return point.proceed(); // 普通方法执行
        }
        IsolationLevel isolationLevel = multiTransaction.isolationLevel();
        boolean readOnly = multiTransaction.readOnly();
        String prevKey = DataSourceContextHolder.getKey();
        MultiTransactionManager multiTransactionManager = Application.resolve(multiTransaction.transactionManager());
        // 切数据源，如果失败使用默认库
        if (multiTransactionManager.switchDataSource(point, signature, multiTransaction)) {
            return point.proceed(); // 主库
        }
        // 开启事务栈
        TransactionHolder transactionHolder = multiTransactionManager.startTransaction(prevKey, isolationLevel, readOnly, multiTransactionManager);
        Object proceed;

        try {
            proceed = point.proceed();
            multiTransactionManager.commit(); // 手动事务提交
        } catch (Throwable ex) {
            log.error("execute method:{}#{},err:", method.getDeclaringClass(), method.getName(), ex);
            multiTransactionManager.rollback(); // 手动事务回滚
            throw ExceptionUtils.api(ex, "系统异常：%s", ex.getMessage());
        } finally {
            // 当前事务结束出栈
            String transId = multiTransactionManager.getTrans().getExecuteStack().pop();
            transactionHolder.getDatasourceKeyStack().pop();
            // 恢复上一层事务
            DataSourceContextHolder.setKey(transactionHolder.getDatasourceKeyStack().peek());
            // 最后回到主事务，关闭此次事务
            multiTransactionManager.close(transId);
        }
        return proceed;

    }


}