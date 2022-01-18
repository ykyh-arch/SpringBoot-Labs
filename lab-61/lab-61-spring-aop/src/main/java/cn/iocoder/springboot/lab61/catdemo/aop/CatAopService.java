package cn.iocoder.springboot.lab61.catdemo.aop;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 开启 CatAop 切面功能
 *
 * @author jaquez
 * @date 2022/01/18 16:15
 **/
@Aspect
@Component
public class CatAopService {

    @Around(value = "@annotation(CatAnnotation)")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();

        Transaction t = Cat.newTransaction("method", method.getName());

        try {
            Object res = pjp.proceed();
            t.setSuccessStatus();
            return res;
        } catch (Throwable e) {
            t.setStatus(e);
            Cat.logError(e);
            throw e;
        } finally {
            t.complete();
        }

    }

}
